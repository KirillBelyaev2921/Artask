function onCaretClick(param) {
  param.parentElement.querySelector(".nested").classList.toggle("active");
  this.classList.toggle("caret-down");
}