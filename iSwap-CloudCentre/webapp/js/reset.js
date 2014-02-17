function reset(f) {
  $(':input',"#"+f)
              .not(':button, :submit, :reset, :hidden')
              .attr('value','')
              .removeAttr('selected');

}