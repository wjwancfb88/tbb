(function() {

  if (window.XuntongJSBridge) {
    
    return;
  };

  ///////////////////////////////////////////////////////////////////////////////////////////////// 
  ///////////////////////////////////鏈湴璋冪敤鐨勫疄闄呴€昏緫////////////////////////////////////////////
  var _CUSTOM_PROTOCOL_SCHEME = 'xuntong',
    callbacksCount = 1,
    callbacks = {};

  function _handleMessageFromXT(callbackId, message) {
	 
    try {
      var callback = callbacks[callbackId];
      if (!callback) return;
      callback.apply(null, [message]);
    } catch (e) {
      alert(e)
    }
  }

  // Use this in javascript to request native objective-c code
  // functionName : string (I think the name is explicit :p)
  // args : array of arguments
  // callback : function with n-arguments that is going to be called when the native code returned
  function _call(functionName, message, callback) {

    var hasCallback = callback && typeof callback == "function";
    var callbackId = hasCallback ? callbacksCount++ : 0;

    if (hasCallback)
      callbacks[callbackId] = callback;

    var iframe = document.createElement("IFRAME");
    iframe.setAttribute("src", _CUSTOM_PROTOCOL_SCHEME + ":" + functionName + ":" + callbackId + ":" + encodeURIComponent(JSON.stringify(message)));
    // For some reason we need to set a non-empty size for the iOS6 simulator...
    iframe.setAttribute("height", "1px");
    iframe.setAttribute("width", "1px");
    document.documentElement.appendChild(iframe);
    iframe.parentNode.removeChild(iframe);
    iframe = null;

  }




  var __XuntongJSBridge = {
    // public
    invoke: _call,
    call: _call,
    handleMessageFromXT: _handleMessageFromXT
  };

  window.XuntongJSBridge = __XuntongJSBridge;

})();