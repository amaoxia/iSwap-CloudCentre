package org.jbpm.pvm.internal.el;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ResourceBundleELResolver;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jbpm.internal.log.Log;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.model.ScopeInstanceImpl;

/**
 * @author Tom Baeyens
 */
public class JbpmElFactoryImpl extends JbpmElFactory {

  private static Log log = Log.getLog(JbpmElFactoryImpl.class.getName());

  Class<?> functionClass = JstlFunction.class;

  /** create ElContext used during parsing time */
  public ELContext createElContext() {
    return createCompositeResolver(null);
  }

  /** create ElContext used during evaluation time related to an execution */
  public ELContext createElContext(ScopeInstanceImpl scopeInstance) {
    return createCompositeResolver(scopeInstance);
  }

  protected ELContext createCompositeResolver(ScopeInstanceImpl scopeInstance) {
    CompositeELResolver compositeELResolver = new CompositeELResolver();

    if (scopeInstance!=null) {
      compositeELResolver.add(new JbpmConstantsElResolver(scopeInstance));
      compositeELResolver.add(new JbpmVariableElResolver(scopeInstance));
    }

    EnvironmentImpl environment = EnvironmentImpl.getCurrent();
    if (environment!=null) {
      compositeELResolver.add(new JbpmEnvironmentElResolver(environment));
    }

    addCdiResolver(compositeELResolver);

    addBasicResolvers(compositeELResolver);

    FunctionMapper functionMapper = createFunctionMapper();

    return createElContext(compositeELResolver, functionMapper);
  }

  protected void addCdiResolver(CompositeELResolver compositeELResolver) {
    BeanManager beanManager = getBeanManager();
    if (beanManager!=null) {
      ELResolver cdiResolver = beanManager.getELResolver();
      if (cdiResolver!=null) {
        compositeELResolver.add(cdiResolver);
        log.debug("added cdi el resolver");
      }
    } else {
      log.debug("no cdi bean manager available in jndi");
    }
  }

  protected BeanManager getBeanManager() {
    try {
      InitialContext initialContext = new InitialContext();
      return (BeanManager) initialContext.lookup("java:comp/BeanManager");
    } catch (NamingException e) {
      return null;
    }
  }

  public ExpressionFactory createExpressionFactory() {
    ExpressionFactory expressionFactory;
    try {
      expressionFactory = ExpressionFactory.newInstance();
    } catch (NoSuchMethodError e) {
      // to support previous version of el-api
      expressionFactory = (ExpressionFactory) FactoryFinder.find(ExpressionFactory.class
        .getName(),"de.odysseus.el.ExpressionFactoryImpl", null, "el.properties");
    }

    BeanManager beanManager = getBeanManager();
    if (beanManager!=null) {
      expressionFactory = beanManager.wrapExpressionFactory(expressionFactory);
    }

    return expressionFactory;
  }

  protected void addBasicResolvers(CompositeELResolver compositeELResolver) {
    compositeELResolver.add(new ResourceBundleELResolver());
    compositeELResolver.add(new MapELResolver());
    compositeELResolver.add(new ListELResolver());
    compositeELResolver.add(new ArrayELResolver());
    compositeELResolver.add(new BeanELResolver());
  }

  protected FunctionMapper createFunctionMapper() {
    return new JbpmFunctionMapper(functionClass);
  }

  protected JbpmElContext createElContext(CompositeELResolver compositeELResolver, FunctionMapper functionMapper) {
    return new JbpmElContext(compositeELResolver, functionMapper);
  }
}
