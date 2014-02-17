/*----------------------------------------------------------------------------\
|                          xLoadTree 2.0 PRE RELEASE                          |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
|             This is a pre release and may not be redistributed.             |
|              Watch http://webfx.eae.net for the final version               |
|-----------------------------------------------------------------------------|
|                   Created by Erik Arvidsson & Emil A Eklund                 |
|                  (http://webfx.eae.net/contact.html#erik)                   |
|                  (http://webfx.eae.net/contact.html#emil)                   |
|                      For WebFX (http://webfx.eae.net/)                      |
|-----------------------------------------------------------------------------|
| A tree menu system for IE 5.5+, Mozilla 1.4+, Opera 7.5+                    |
|-----------------------------------------------------------------------------|
|     Copyright (c) 2003, 2004, 2005, 2006 Erik Arvidsson & Emil A Eklund     |
|-----------------------------------------------------------------------------|
| Licensed under the Apache License, Version 2.0 (the "License"); you may not |
| use this file except in compliance with the License.  You may obtain a copy |
| of the License at http://www.apache.org/licenses/LICENSE-2.0                |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| Unless  required  by  applicable law or  agreed  to  in  writing,  software |
| distributed under the License is distributed on an  "AS IS" BASIS,  WITHOUT |
| WARRANTIES OR  CONDITIONS OF ANY KIND,  either express or implied.  See the |
| License  for the  specific language  governing permissions  and limitations |
| under the License.                                                          |
|-----------------------------------------------------------------------------|
| Dependencies: xtree2.js  - Supplies the tree control                        |
|               xtree2.css - Used to define the look and feel                 |
|-----------------------------------------------------------------------------|
| 2004-02-21 | Pre release distributed to a few selected tester               |
| 2005-06-06 | Removed dependency on XML Extras                               |
| 2006-05-28 | Changed license to Apache Software License 2.0.                |
|-----------------------------------------------------------------------------|
| Created 2003-??-?? | All changes are in the log above. | Updated 2006-05-28 |
\----------------------------------------------------------------------------*/


webFXTreeConfig.loadingText = "Loading...";
webFXTreeConfig.loadingIcon = "${path}/js/xloadtree/images/loading.gif";


function WebFXLoadTree(sText, sXmlSrc, oAction, sBehavior, sIcon, sOpenIcon) {
	WebFXTree.call(this, sText, oAction, sBehavior, sIcon, sOpenIcon);

	// setup default property values
	this.src = sXmlSrc;
	this.loading = !sXmlSrc;
	this.loaded = !sXmlSrc;
	this.errorText = "";

	if (this.src) {
		/// add loading Item
		this._loadingItem = WebFXLoadTree.createLoadingItem();
		this.add(this._loadingItem);

		if (this.getExpanded()) {
			WebFXLoadTree.loadXmlDocument(this);
		}
	}
}

WebFXLoadTree.createLoadingItem = function () {
	return new WebFXTreeItem(webFXTreeConfig.loadingText, null, null,
							 webFXTreeConfig.loadingIcon);
};

_p = WebFXLoadTree.prototype = new WebFXTree;

_p.setExpanded = function setExpanded(b) {
	WebFXTree.prototype.setExpanded.call(this, b);

	if (this.src && b) {
		if (!this.loaded && !this.loading) {
			// load
			WebFXLoadTree.loadXmlDocument(this);
		}
	}
};

function WebFXLoadTreeItem(sText, sXmlSrc, oAction, eParent, sIcon, sOpenIcon) {
	WebFXTreeItem.call(this, sText, oAction, eParent, sIcon, sOpenIcon);

// setup default property values
	this.src = sXmlSrc;
	this.loading = !sXmlSrc;
	this.loaded = !sXmlSrc;
	this.errorText = "";

	if (this.src) {
		/// add loading Item
		this._loadingItem = WebFXLoadTree.createLoadingItem();
		this.add(this._loadingItem);

		if (this.getExpanded()) {
			WebFXLoadTree.loadXmlDocument(this);
		}
	}
}

_p = WebFXLoadTreeItem.prototype = new WebFXTreeItem;

_p.setExpanded = function setExpanded(b) {
	WebFXTreeItem.prototype.setExpanded.call(this, b);

	if (this.src && b) {
		if (!this.loaded && !this.loading) {
			// load
			WebFXLoadTree.loadXmlDocument(this);
		}
	}
};

// reloads the src file if already loaded
WebFXLoadTree.prototype.reload =
_p.reload = function reload() {
	// if loading do nothing
	if (this.loaded) {
		var t = this.getTree();
		var expanded = this.getExpanded();
		var sr = t.getSuspendRedraw();
		t.setSuspendRedraw(true);

		// remove
		while (this.childNodes.length > 0) {
			this.remove(this.childNodes[this.childNodes.length - 1]);
		}

		this.loaded = false;

		this._loadingItem = WebFXLoadTree.createLoadingItem();
		this.add(this._loadingItem);

		if (expanded) {
			this.setExpanded(true);
		}

		t.setSuspendRedraw(sr);
		this.update();
	} else if (this.open && !this.loading) {
		WebFXLoadTree.loadXmlDocument(this);
	}
};



WebFXLoadTree.prototype.setSrc =
_p.setSrc = function setSrc(sSrc) {
	var oldSrc = this.src;
	if (sSrc == oldSrc) return;

	var expanded = this.getExpanded();

	// remove all
	this._callSuspended(function () {
		// remove
		while (this.childNodes.length > 0)
			this.remove(this.childNodes[this.childNodes.length - 1]);
	});
	this.update();

	this.loaded = false;
	this.loading = false;
	if (this._loadingItem) {
		this._loadingItem.dispose();
		this._loadingItem = null;
	}
	this.src = sSrc;

	if (sSrc) {
		this._loadingItem = WebFXLoadTree.createLoadingItem();
		this.add(this._loadingItem);
	}

	this.setExpanded(expanded);
};

WebFXLoadTree.prototype.getSrc =
_p.getSrc = function getSrc() {
	return this.src;
};

WebFXLoadTree.prototype.dispose = function () {
	WebFXTree.prototype.dispose.call(this);
	if (this._xmlHttp)
	{
		if (this._xmlHttp.dispose) {
			this._xmlHttp.dispose();
		}
		try {
			this._xmlHttp.onreadystatechange = null;
			this._xmlHttp.abort();
		} catch (ex) {}
		this._xmlHttp = null;
	}
};

_p.dispose = function dispose() {
	WebFXTreeItem.prototype.dispose.call(this);
	if (this._xmlHttp) {
		if (this._xmlHttp.dispose) {
			this._xmlHttp.dispose();
		}
		try {
			this._xmlHttp.onreadystatechange = null;
			this._xmlHttp.abort();
		} catch (ex) {}
		this._xmlHttp = null;
	}
};


// The path is divided by '/' and the item is identified by the text
WebFXLoadTree.prototype.openPath =
_p.openPath = function openPath(sPath, bSelect, bFocus) {
	// remove any old pending paths to open
	delete this._pathToOpen;
	//delete this._pathToOpenById;
	this._selectPathOnLoad = bSelect;
	this._focusPathOnLoad = bFocus;

	if (sPath == "") {
		if (bSelect) {
			this.select();
		}
		if (bFocus) {
			window.setTimeout("WebFXTreeAbstractNode._onTimeoutFocus(\"" + this.getId() + "\")", 10);
		}
		return;
	}

	var parts = sPath.split("/");
	var remainingPath = parts.slice(1).join("/");

	if (sPath.charAt(0) == "/") {
		this.getTree().openPath(remainingPath, bSelect, bFocus);
	} else {
		// open
		this.setExpanded(true);
		if (this.loaded) {
			parts = sPath.split("/");
			var ti = this.findChildByText(parts[0]);
			if (!ti) {
				throw "Could not find child node with text \"" + parts[0] + "\"";
			}

			ti.openPath(remainingPath, bSelect, bFocus);
		} else {
			this._pathToOpen = sPath;
		}
	}
};


// Opera has some serious attribute problems. We need to use getAttribute
// for certain attributes
WebFXLoadTree._attrs = ["text", "src", "action", "id", "target","ischart"];

WebFXLoadTree.createItemFromElement = function (oNode) {
	var jsAttrs = {};
	var domAttrs = oNode.attributes;
	var i, l;

	l = domAttrs.length;
	for (i = 0; i < l; i++) {
		if (domAttrs[i] == null) {
			continue;
		}
		jsAttrs[domAttrs[i].nodeName] = domAttrs[i].nodeValue;
	}

	var name, val;
	for (i = 0; i < WebFXLoadTree._attrs.length; i++) {
		name = WebFXLoadTree._attrs[i];
		value = oNode.getAttribute(name);
		if (value) {
			jsAttrs[name] = value;
		}
	}

	var action;
	if (jsAttrs.onaction) {
		action = new Function(jsAttrs.onaction);
	} else if (jsAttrs.action) {
		action = jsAttrs.action;
	}
	//liuw updateÅÐ¶ÏÊÇ·ñÎªÍ¼±í
	if(jsAttrs.ischart=="no"){
		jsAttrs.icon=jQuery("#_h_path").val()+"/js/xloadtree/images/foldericon_chart.gif";
		jsAttrs.openIcon=jQuery("#_h_path").val()+"/js/xloadtree/images/openfoldericon_chart.gif";
	}
	var jsNode = new WebFXLoadTreeItem(jsAttrs.html || "", jsAttrs.src, action,
									   null, jsAttrs.icon, jsAttrs.openIcon);
	
	if (jsAttrs.text) {
		jsNode.setText(jsAttrs.text);
	}

	if (jsAttrs.target) {
		jsNode.target = jsAttrs.target;
	}
	if (jsAttrs.id) {
		jsNode.setId(jsAttrs.id);
	}
	if (jsAttrs.toolTip) {
		jsNode.toolTip = jsAttrs.toolTip;
	}
	if (jsAttrs.expanded) {
		jsNode.setExpanded(jsAttrs.expanded != "false");
	}
	if (jsAttrs.onload) {
		jsNode.onload = new Function(jsAttrs.onload);
	}
	if (jsAttrs.onerror) {
		jsNode.onerror = new Function(jsAttrs.onerror);
	}

	jsNode.attributes = jsAttrs;

	// go through childNodes
	var cs = oNode.childNodes;
	l = cs.length;
	for (i = 0; i < l; i++) {
		if (cs[i].tagName == "tree") {
			jsNode.add(WebFXLoadTree.createItemFromElement(cs[i]));
		}
	}

	return jsNode;
};

WebFXLoadTree.loadXmlDocument = function (jsNode) {
	if (jsNode.loading || jsNode.loaded) {
		return;
	}
	jsNode.loading = true;
	var id = jsNode.getId();
	jsNode._xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest : new window.ActiveXObject("Microsoft.XmlHttp");
	jsNode._xmlHttp.open("GET", jsNode.src, true);	// async
	jsNode._xmlHttp.onreadystatechange = new Function("WebFXLoadTree._onload(\"" + id + "\")");

	// call in new thread to allow ui to update
	window.setTimeout("WebFXLoadTree._ontimeout(\"" + id + "\")", 10);
};

WebFXLoadTree._onload = function (sId) {
	var jsNode = webFXTreeHandler.all[sId];
	if (jsNode._xmlHttp.readyState == 4) {
		WebFXLoadTree.documentLoaded(jsNode);
		webFXLoadTreeQueue.remove(jsNode);
		if (jsNode._xmlHttp.dispose)
			jsNode._xmlHttp.dispose();
		jsNode._xmlHttp = null;
	}
};

WebFXLoadTree._ontimeout = function (sId) {
	var jsNode = webFXTreeHandler.all[sId];
	webFXLoadTreeQueue.add(jsNode);
};



// Inserts an xml document as a subtree to the provided node
WebFXLoadTree.documentLoaded = function (jsNode) {
	if (jsNode.loaded) {
		return;
	}

	jsNode.errorText = "";
	jsNode.loaded = true;
	jsNode.loading = false;

	var t = jsNode.getTree();
	var oldSuspend = t.getSuspendRedraw();
	t.setSuspendRedraw(true);

	var doc = jsNode._xmlHttp.responseXML;

	// check that the load of the xml file went well
	if(!doc || doc.parserError && doc.parseError.errorCode != 0 || !doc.documentElement) {
		if (!doc || doc.parseError.errorCode == 0) {
			jsNode.errorText = "Error loading " + jsNode.src + " (" + jsNode._xmlHttp.status + ": " + jsNode._xmlHttp.statusText + ")";
		} else {
			jsNode.errorText = "Error loading " + jsNode.src + " (" + doc.parseError.reason + ")";
		}
	} else {
		// there is one extra level of tree elements
		var root = doc.documentElement;

		// loop through all tree children
		var cs = root.childNodes;
		var l = cs.length;
		for (var i = 0; i < l; i++) {
			if (cs[i].tagName == "tree") {
				jsNode.add(WebFXLoadTree.createItemFromElement(cs[i]));
			}
		}
	}

	if (jsNode.errorText != "") {
		jsNode._loadingItem.icon = "${path}/js/xloadtree/images/exclamation.16.gif";
		jsNode._loadingItem.text = jsNode.errorText;
		jsNode._loadingItem.action = WebFXLoadTree._reloadParent;
		jsNode._loadingItem.toolTip = "Click to reload";

		t.setSuspendRedraw(oldSuspend);

		jsNode._loadingItem.update();

		if (typeof jsNode.onerror == "function") {
			jsNode.onerror();
		}
	} else {
		// remove dummy
		if (jsNode._loadingItem != null) {
			jsNode.remove(jsNode._loadingItem);
		}

		if (jsNode._pathToOpen) {
			jsNode.openPath(jsNode._pathToOpen, jsNode._selectPathOnLoad, jsNode._focusPathOnLoad);
		}

		t.setSuspendRedraw(oldSuspend);
		jsNode.update();
		if (typeof jsNode.onload == "function") {
			jsNode.onload();
		}
	}
};

WebFXLoadTree._reloadParent = function () {
	this.getParent().reload();
};







var webFXLoadTreeQueue = new function () {
	var nodes = [];
	var ie = /msie/i.test(navigator.userAgent);
	var opera = /opera/i.test(navigator.userAgent);

	this.add = function (jsNode) {
		if (ie || opera) {
			nodes.push(jsNode);
			if (nodes.length == 1) {
				send();
			}
		} else {
			jsNode._xmlHttp.send(null);
		}
	};

	this.remove = function (jsNode) {
		if (ie || opera) {
			arrayHelper.remove(nodes, jsNode);
			if (nodes.length > 0) {
				send();
			}
		}
	};

	// IE only
	function send() {
		var id = nodes[0].getId();
		var jsNode = webFXTreeHandler.all[id];
		if (!jsNode) {
			return;
		}
		// if no _xmlHttp then remove it
		if (!jsNode._xmlHttp) {
			this.remove(jsNode);
		} else {
			jsNode._xmlHttp.send(null);
		}
	}
};
