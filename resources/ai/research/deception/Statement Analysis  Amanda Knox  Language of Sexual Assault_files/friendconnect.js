window.google = window["google"] || {};google.friendconnect = google.friendconnect_ || {};if (!window['__ps_loaded__']) {/*http://www-a-fc-opensocial.googleusercontent.com/gadgets/js/rpc:core.util.js?c=1*/
window['___jsl'] = window['___jsl'] || {};(window['___jsl']['ci'] = (window['___jsl']['ci'] || [])).push({"rpc":{"commSwf":"//xpc.googleusercontent.com/gadgets/xpc.swf","passReferrer":"p2c:query","parentRelayUrl":"/rpc_relay.html"}});window['___jsl']=window['___jsl']||{};(window['___jsl']['ci'] = (window['___jsl']['ci'] || [])).push({"rpc":{"commSwf":"//xpc.googleusercontent.com/gadgets/xpc.swf","passReferrer":"p2c:query","parentRelayUrl":"/rpc_relay.html"}});
/* [start] feature=taming */
var safeJSON=window.safeJSON;
var tamings___=window.tamings___||[];
var bridge___;
var caja___=window.caja___;
var ___=window.___;;

/* [end] feature=taming */

/* [start] feature=gapi-globals */
var gapi=window.gapi||{};gapi.client=window.gapi&&window.gapi.client||{};
;
;

/* [end] feature=gapi-globals */

/* [start] feature=globals */
var gadgets=window.gadgets||{},shindig=window.shindig||{},osapi=window.osapi=window.osapi||{},google=window.google||{};
;
;

/* [end] feature=globals */

/* [start] feature=core.config.base */
window['___cfg'] = window['___cfg'] || window['___gcfg'];;
if(!window.gadgets["config"]){gadgets.config=function(){var f;
var h={};
var b={};
function c(j,l){for(var k in l){if(!l.hasOwnProperty(k)){continue
}if(typeof j[k]==="object"&&typeof l[k]==="object"){c(j[k],l[k])
}else{j[k]=l[k]
}}}function i(){var j=document.scripts||document.getElementsByTagName("script");
if(!j||j.length==0){return null
}var m;
if(f.u){for(var k=0;
!m&&k<j.length;
++k){var l=j[k];
if(l.src&&l.src.indexOf(f.u)==0){m=l
}}}if(!m){m=j[j.length-1]
}if(!m.src){return null
}return m
}function a(j){var k="";
if(j.nodeType==3||j.nodeType==4){k=j.nodeValue
}else{if(j.innerText){k=j.innerText
}else{if(j.innerHTML){k=j.innerHTML
}else{if(j.firstChild){var l=[];
for(var m=j.firstChild;
m;
m=m.nextSibling){l.push(a(m))
}k=l.join("")
}}}}return k
}function e(k){if(!k){return{}
}var j;
while(k.charCodeAt(k.length-1)==0){k=k.substring(0,k.length-1)
}try{j=(new Function("return ("+k+"\n)"))()
}catch(l){}if(typeof j==="object"){return j
}try{j=(new Function("return ({"+k+"\n})"))()
}catch(l){}return typeof j==="object"?j:{}
}function g(n){var p=window.___cfg;
if(p){c(n,p)
}var o=i();
if(!o){return
}var k=a(o);
var j=e(k);
if(f.f&&f.f.length==1){var m=f.f[0];
if(!j[m]){var l={};
l[f.f[0]]=j;
j=l
}}c(n,j)
}function d(o){for(var l in h){if(h.hasOwnProperty(l)){var n=h[l];
for(var m=0,k=n.length;
m<k;
++m){o(l,n[m])
}}}}return{register:function(l,k,j,m){var n=h[l];
if(!n){n=[];
h[l]=n
}n.push({validators:k||{},callback:j,callOnUpdate:m})
},get:function(j){if(j){return b[j]||{}
}return b
},init:function(k,j){f=window.___jsl||{};
c(b,k);
g(b);
var l=window.___config||{};
c(b,l);
d(function(q,p){var o=b[q];
if(o&&!j){var m=p.validators;
for(var n in m){if(m.hasOwnProperty(n)){if(!m[n](o[n])){throw new Error('Invalid config value "'+o[n]+'" for parameter "'+n+'" in component "'+q+'"')
}}}}if(p.callback){p.callback(b)
}})
},update:function(k,p){var o=(window.gapi&&window.gapi["config"]&&window.gapi["config"]["update"]);
if(!p&&o){o(k)
}var n=[];
d(function(q,j){if(k.hasOwnProperty(q)||(p&&b&&b[q])){if(j.callback&&j.callOnUpdate){n.push(j.callback)
}}});
b=p?{}:b||{};
c(b,k);
for(var m=0,l=n.length;
m<l;
++m){n[m](b)
}}}
}()
}else{gadgets.config=window.gadgets["config"];
gadgets.config.register=gadgets.config.register;
gadgets.config.get=gadgets.config.get;
gadgets.config.init=gadgets.config.init;
gadgets.config.update=gadgets.config.update
};;

/* [end] feature=core.config.base */

/* [start] feature=core.log */
gadgets.log=(function(){var e=1;
var a=2;
var f=3;
var c=4;
var d=function(i){b(e,i)
};
gadgets.warn=function(i){b(a,i)
};
gadgets.error=function(i){b(f,i)
};
gadgets.setLogLevel=function(i){h=i
};
function b(k,i){if(k<h||!g){return
}if(k===a&&g.warn){g.warn(i)
}else{if(k===f&&g.error){try{g.error(i)
}catch(j){}}else{if(g.log){g.log(i)
}}}}d.INFO=e;
d.WARNING=a;
d.NONE=c;
var h=e;
var g=window.console?window.console:window.opera?window.opera.postError:undefined;
return d
})();;
;

/* [end] feature=core.log */

/* [start] feature=core.config */
(function(){gadgets.config.EnumValidator=function(d){var c=[];
if(arguments.length>1){for(var b=0,a;
(a=arguments[b]);
++b){c.push(a)
}}else{c=d
}return function(f){for(var e=0,g;
(g=c[e]);
++e){if(f===c[e]){return true
}}return false
}
};
gadgets.config.RegExValidator=function(a){return function(b){return a.test(b)
}
};
gadgets.config.ExistsValidator=function(a){return typeof a!=="undefined"
};
gadgets.config.NonEmptyStringValidator=function(a){return typeof a==="string"&&a.length>0
};
gadgets.config.BooleanValidator=function(a){return typeof a==="boolean"
};
gadgets.config.LikeValidator=function(a){return function(c){for(var d in a){if(a.hasOwnProperty(d)){var b=a[d];
if(!b(c[d])){return false
}}}return true
}
}
})();;

/* [end] feature=core.config */

/* [start] feature=core.util.base */
gadgets.util=gadgets.util||{};
(function(){gadgets.util.makeClosure=function(d,f,e){var c=[];
for(var b=2,a=arguments.length;
b<a;
++b){c.push(arguments[b])
}return function(){var g=c.slice();
for(var k=0,h=arguments.length;
k<h;
++k){g.push(arguments[k])
}return f.apply(d,g)
}
};
gadgets.util.makeEnum=function(b){var c,a,d={};
for(c=0;
(a=b[c]);
++c){d[a]=a
}return d
}
})();;

/* [end] feature=core.util.base */

/* [start] feature=core.util.dom */
gadgets.util=gadgets.util||{};
(function(){var c="http://www.w3.org/1999/xhtml";
function b(f,e){var h=e||{};
for(var g in h){if(h.hasOwnProperty(g)){f[g]=h[g]
}}}function d(g,f){var e=["<",g];
var i=f||{};
for(var h in i){if(i.hasOwnProperty(h)){e.push(" ");
e.push(h);
e.push('="');
e.push(gadgets.util.escapeString(i[h]));
e.push('"')
}}e.push("></");
e.push(g);
e.push(">");
return e.join("")
}function a(f){var g="";
if(f.nodeType==3||f.nodeType==4){g=f.nodeValue
}else{if(f.innerText){g=f.innerText
}else{if(f.innerHTML){g=f.innerHTML
}else{if(f.firstChild){var e=[];
for(var h=f.firstChild;
h;
h=h.nextSibling){e.push(a(h))
}g=e.join("")
}}}}return g
}gadgets.util.createElement=function(f){var e;
if((!document.body)||document.body.namespaceURI){try{e=document.createElementNS(c,f)
}catch(g){}}return e||document.createElement(f)
};
gadgets.util.createIframeElement=function(g){var i=gadgets.util.createElement("iframe");
try{var e=d("iframe",g);
var f=gadgets.util.createElement(e);
if(f&&((!i)||((f.tagName==i.tagName)&&(f.namespaceURI==i.namespaceURI)))){i=f
}}catch(h){}b(i,g);
return i
};
gadgets.util.getBodyElement=function(){if(document.body){return document.body
}try{var f=document.getElementsByTagNameNS(c,"body");
if(f&&(f.length==1)){return f[0]
}}catch(e){}return document.documentElement||document
};
gadgets.util.getInnerText=function(e){return a(e)
}
})();;

/* [end] feature=core.util.dom */

/* [start] feature=core.util.event */
gadgets.util=gadgets.util||{};
(function(){gadgets.util.attachBrowserEvent=function(c,b,d,a){if(typeof c.addEventListener!="undefined"){c.addEventListener(b,d,a)
}else{if(typeof c.attachEvent!="undefined"){c.attachEvent("on"+b,d)
}else{gadgets.warn("cannot attachBrowserEvent: "+b)
}}};
gadgets.util.removeBrowserEvent=function(c,b,d,a){if(c.removeEventListener){c.removeEventListener(b,d,a)
}else{if(c.detachEvent){c.detachEvent("on"+b,d)
}else{gadgets.warn("cannot removeBrowserEvent: "+b)
}}}
})();;

/* [end] feature=core.util.event */

/* [start] feature=core.util.onload */
gadgets.util=gadgets.util||{};
(function(){var a=[];
gadgets.util.registerOnLoadHandler=function(b){a.push(b)
};
gadgets.util.runOnLoadHandlers=function(){for(var c=0,b=a.length;
c<b;
++c){a[c]()
}}
})();;

/* [end] feature=core.util.onload */

/* [start] feature=core.util.string */
gadgets.util=gadgets.util||{};
(function(){var a={0:false,10:true,13:true,34:true,39:true,60:true,62:true,92:true,8232:true,8233:true,65282:true,65287:true,65308:true,65310:true,65340:true};
function b(c,d){return String.fromCharCode(d)
}gadgets.util.escape=function(c,g){if(!c){return c
}else{if(typeof c==="string"){return gadgets.util.escapeString(c)
}else{if(typeof c==="array"){for(var f=0,d=c.length;
f<d;
++f){c[f]=gadgets.util.escape(c[f])
}}else{if(typeof c==="object"&&g){var e={};
for(var h in c){if(c.hasOwnProperty(h)){e[gadgets.util.escapeString(h)]=gadgets.util.escape(c[h],true)
}}return e
}}}}return c
};
gadgets.util.escapeString=function(g){if(!g){return g
}var d=[],f,h;
for(var e=0,c=g.length;
e<c;
++e){f=g.charCodeAt(e);
h=a[f];
if(h===true){d.push("&#",f,";")
}else{if(h!==false){d.push(g.charAt(e))
}}}return d.join("")
};
gadgets.util.unescapeString=function(c){if(!c){return c
}return c.replace(/&#([0-9]+);/g,b)
}
})();;

/* [end] feature=core.util.string */

/* [start] feature=core.util.urlparams */
gadgets.util=gadgets.util||{};
(function(){var a=null;
function b(e){var f;
var c=e.indexOf("?");
var d=e.indexOf("#");
if(d===-1){f=e.substr(c+1)
}else{f=[e.substr(c+1,d-c-1),"&",e.substr(d+1)].join("")
}return f.split("&")
}gadgets.util.getUrlParameters=function(p){var d=typeof p==="undefined";
if(a!==null&&d){return a
}var l={};
var f=b(p||document.location.href);
var n=window.decodeURIComponent?decodeURIComponent:unescape;
for(var h=0,g=f.length;
h<g;
++h){var m=f[h].indexOf("=");
if(m===-1){continue
}var c=f[h].substring(0,m);
var o=f[h].substring(m+1);
o=o.replace(/\+/g," ");
try{l[c]=n(o)
}catch(k){}}if(d){a=l
}return l
};
gadgets.util.getUrlParameters()
})();;

/* [end] feature=core.util.urlparams */

/* [start] feature=core.util */
gadgets.util=gadgets.util||{};
(function(){var b={};
var a={};
function c(d){b=d["core.util"]||{}
}if(gadgets.config){gadgets.config.register("core.util",null,c)
}gadgets.util.getFeatureParameters=function(d){return typeof b[d]==="undefined"?null:b[d]
};
gadgets.util.hasFeature=function(d){return typeof b[d]!=="undefined"
};
gadgets.util.getServices=function(){return a
}
})();;

/* [end] feature=core.util */

/* [start] feature=core.json */
if(window.JSON&&window.JSON.parse&&window.JSON.stringify){gadgets.json=(function(){var a=/___$/;
function b(d,e){var c=this[d];
return c
}return{parse:function(d){try{return window.JSON.parse(d)
}catch(c){return false
}},stringify:function(d){var h=window.JSON.stringify;
function f(e){return h.call(this,e,b)
}var g=(Array.prototype.toJSON&&h([{x:1}])==='"[{\\"x\\": 1}]"')?f:h;
try{return g(d,function(i,e){return !a.test(i)?e:void 0
})
}catch(c){return null
}}}
})()
};;
;
if(!(window.JSON&&window.JSON.parse&&window.JSON.stringify)){gadgets.json=function(){function f(n){return n<10?"0"+n:n
}Date.prototype.toJSON=function(){return[this.getUTCFullYear(),"-",f(this.getUTCMonth()+1),"-",f(this.getUTCDate()),"T",f(this.getUTCHours()),":",f(this.getUTCMinutes()),":",f(this.getUTCSeconds()),"Z"].join("")
};
var m={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};
function stringify(value){var a,i,k,l,r=/[\"\\\x00-\x1f\x7f-\x9f]/g,v;
switch(typeof value){case"string":return r.test(value)?'"'+value.replace(r,function(a){var c=m[a];
if(c){return c
}c=a.charCodeAt();
return"\\u00"+Math.floor(c/16).toString(16)+(c%16).toString(16)
})+'"':'"'+value+'"';
case"number":return isFinite(value)?String(value):"null";
case"boolean":case"null":return String(value);
case"object":if(!value){return"null"
}a=[];
if(typeof value.length==="number"&&!value.propertyIsEnumerable("length")){l=value.length;
for(i=0;
i<l;
i+=1){a.push(stringify(value[i])||"null")
}return"["+a.join(",")+"]"
}for(k in value){if(/___$/.test(k)){continue
}if(value.hasOwnProperty(k)){if(typeof k==="string"){v=stringify(value[k]);
if(v){a.push(stringify(k)+":"+v)
}}}}return"{"+a.join(",")+"}"
}return""
}return{stringify:stringify,parse:function(text){if(/^[\],:{}\s]*$/.test(text.replace(/\\["\\\/b-u]/g,"@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,"]").replace(/(?:^|:|,)(?:\s*\[)+/g,""))){return eval("("+text+")")
}return false
}}
}()
};;
gadgets.json.flatten=function(c){var d={};
if(c===null||c===undefined){return d
}for(var a in c){if(c.hasOwnProperty(a)){var b=c[a];
if(null===b||undefined===b){continue
}d[a]=(typeof b==="string")?b:gadgets.json.stringify(b)
}}return d
};;

/* [end] feature=core.json */

/* [start] feature=rpc */
gadgets.rpctx=gadgets.rpctx||{};
if(!gadgets.rpctx.wpm){gadgets.rpctx.wpm=function(){var e,d;
var c=true;
function b(h,j,g){if(typeof window.addEventListener!="undefined"){window.addEventListener(h,j,g)
}else{if(typeof window.attachEvent!="undefined"){window.attachEvent("on"+h,j)
}}if(h==="message"){window.___jsl=window.___jsl||{};
var i=window.___jsl;
i.RPMQ=i.RPMQ||[];
i.RPMQ.push(j)
}}function a(h,i,g){if(window.removeEventListener){window.removeEventListener(h,i,g)
}else{if(window.detachEvent){window.detachEvent("on"+h,i)
}}}function f(h){var i=gadgets.json.parse(h.data);
if(!i||!i.f){return
}var g=gadgets.rpc.getTargetOrigin(i.f);
if(c&&(typeof h.origin!=="undefined"?h.origin!==g:h.domain!==/^.+:\/\/([^:]+).*/.exec(g)[1])){gadgets.error("Invalid rpc message origin. "+g+" vs "+(h.origin||""));
return
}e(i,h.origin)
}return{getCode:function(){return"wpm"
},isParentVerifiable:function(){return true
},init:function(h,i){function g(k){var j=k&&k.rpc||{};
if(String(j.disableForceSecure)==="true"){c=false
}}gadgets.config.register("rpc",null,g);
e=h;
d=i;
b("message",f,false);
d("..",true);
return true
},setup:function(h,g){d(h,true);
return true
},call:function(h,k,j){var g=gadgets.rpc.getTargetOrigin(h);
var i=gadgets.rpc._getTargetWin(h);
if(g){window.setTimeout(function(){i.postMessage(gadgets.json.stringify(j),g)
},0)
}else{if(h!=".."){gadgets.error("No relay set (used as window.postMessage targetOrigin), cannot send cross-domain message")
}}return true
}}
}()
};;
gadgets.rpctx=gadgets.rpctx||{};
if(!gadgets.rpctx.flash){gadgets.rpctx.flash=function(){var z="___xpcswf";
var q=null;
var j=false;
var k=null;
var G=null;
var u=null;
var H=100;
var r=50;
var A=[];
var I=null;
var a=0;
var v="_scr";
var f="_pnt";
var i=100;
var p=50;
var m=0;
var e=null;
var y={};
var C=window.location.protocol+"//"+window.location.host;
var n="___jsl";
var d="_fm";
var h;
function t(){window[n]=window[n]||{};
var K=window[n];
var L=K[d]={};
h=n+"."+d;
return L
}var l=t();
function J(M,K){var L=function(){M.apply({},arguments)
};
l[K]=l[K]||L;
return h+"."+K
}function o(K){return K===".."?gadgets.rpc.RPC_ID:K
}function D(K){return K===".."?"INNER":"OUTER"
}function F(K){if(j){q=K&&K.rpc&&K.rpc["commSwf"]||"//xpc.googleusercontent.com/gadgets/xpc.swf"
}}gadgets.config.register("rpc",null,F);
function w(){if(u===null&&document.body&&q){var M=q+"?cb="+Math.random()+"&origin="+C+"&jsl=1";
var L=document.createElement("div");
L.style.height="1px";
L.style.width="1px";
var K='<object height="1" width="1" id="'+z+'" type="application/x-shockwave-flash"><param name="allowScriptAccess" value="always"></param><param name="movie" value="'+M+'"></param><embed type="application/x-shockwave-flash" allowScriptAccess="always" src="'+M+'" height="1" width="1"></embed></object>';
document.body.appendChild(L);
L.innerHTML=K;
u=L.firstChild
}++a;
if(I!==null&&(u!==null||a>=r)){window.clearTimeout(I)
}else{I=window.setTimeout(w,H)
}}function B(){if(y[".."]){return
}x("..");
m++;
if(m>=p&&e!==null){window.clearTimeout(e);
e=null
}else{e=window.setTimeout(B,i)
}}function E(){if(u!==null&&u.setup){while(A.length>0){var L=A.shift();
var K=L.targetId;
u.setup(L.token,o(K),D(K))
}}}function g(){E();
if(I!==null){window.clearTimeout(I)
}I=null
}J(g,"ready");
function b(){if(!y[".."]&&e===null){e=window.setTimeout(B,i)
}}J(b,"setupDone");
function c(M,Q,O){var L=gadgets.rpc.getTargetOrigin(M);
var P=gadgets.rpc.getAuthToken(M);
var K="sendMessage_"+o(M)+"_"+P+"_"+D(M);
var N=u[K];
N.call(u,gadgets.json.stringify(O),L);
return true
}function s(M,O,N){var K=gadgets.json.parse(M);
var L=K[v];
if(L){G(L,true);
y[L]=true;
if(L!==".."){x(L,true)
}return
}window.setTimeout(function(){k(K,O)
},0)
}J(s,"receiveMessage");
function x(N,M){var K=gadgets.rpc.RPC_ID;
var L={};
L[v]=M?"..":K;
L[f]=K;
c(N,K,L)
}return{getCode:function(){return"flash"
},isParentVerifiable:function(){return true
},init:function(L,K){k=L;
G=K;
j=true;
return true
},setup:function(L,K){A.push({token:K,targetId:L});
if(u===null&&I===null){I=window.setTimeout(w,H)
}return true
},call:c,_receiveMessage:s,_ready:g,_setupDone:b}
}()
};;
gadgets.rpctx=gadgets.rpctx||{};
if(!gadgets.rpctx.frameElement){gadgets.rpctx.frameElement=function(){var e="__g2c_rpc";
var b="__c2g_rpc";
var d;
var c;
function a(g,k,j){try{if(k!==".."){var f=window.frameElement;
if(typeof f[e]==="function"){if(typeof f[e][b]!=="function"){f[e][b]=function(l){d(gadgets.json.parse(l))
}
}f[e](gadgets.json.stringify(j));
return true
}}else{var i=document.getElementById(g);
if(typeof i[e]==="function"&&typeof i[e][b]==="function"){i[e][b](gadgets.json.stringify(j));
return true
}}}catch(h){}return false
}return{getCode:function(){return"fe"
},isParentVerifiable:function(){return false
},init:function(f,g){d=f;
c=g;
return true
},setup:function(j,f){if(j!==".."){try{var i=document.getElementById(j);
i[e]=function(k){d(gadgets.json.parse(k))
}
}catch(h){return false
}}if(j===".."){c("..",true);
var g=function(){window.setTimeout(function(){gadgets.rpc.call(j,gadgets.rpc.ACK)
},500)
};
gadgets.util.registerOnLoadHandler(g)
}return true
},call:function(f,h,g){return a(f,h,g)
}}
}()
};;
gadgets.rpctx=gadgets.rpctx||{};
if(!gadgets.rpctx.nix){gadgets.rpctx.nix=function(){var c="GRPC____NIXVBS_wrapper";
var d="GRPC____NIXVBS_get_wrapper";
var f="GRPC____NIXVBS_handle_message";
var b="GRPC____NIXVBS_create_channel";
var a=10;
var j=500;
var i={};
var h;
var g=0;
function e(){var l=i[".."];
if(l){return
}if(++g>a){gadgets.warn("Nix transport setup failed, falling back...");
h("..",false);
return
}if(!l&&window.opener&&"GetAuthToken" in window.opener){l=window.opener;
if(l.GetAuthToken()==gadgets.rpc.getAuthToken("..")){var k=gadgets.rpc.getAuthToken("..");
l.CreateChannel(window[d]("..",k),k);
i[".."]=l;
window.opener=null;
h("..",true);
return
}}window.setTimeout(function(){e()
},j)
}return{getCode:function(){return"nix"
},isParentVerifiable:function(){return false
},init:function(l,m){h=m;
if(typeof window[d]!=="unknown"){window[f]=function(o){window.setTimeout(function(){l(gadgets.json.parse(o))
},0)
};
window[b]=function(o,q,p){if(gadgets.rpc.getAuthToken(o)===p){i[o]=q;
h(o,true)
}};
var k="Class "+c+"\n Private m_Intended\nPrivate m_Auth\nPublic Sub SetIntendedName(name)\n If isEmpty(m_Intended) Then\nm_Intended = name\nEnd If\nEnd Sub\nPublic Sub SetAuth(auth)\n If isEmpty(m_Auth) Then\nm_Auth = auth\nEnd If\nEnd Sub\nPublic Sub SendMessage(data)\n "+f+"(data)\nEnd Sub\nPublic Function GetAuthToken()\n GetAuthToken = m_Auth\nEnd Function\nPublic Sub CreateChannel(channel, auth)\n Call "+b+"(m_Intended, channel, auth)\nEnd Sub\nEnd Class\nFunction "+d+"(name, auth)\nDim wrap\nSet wrap = New "+c+"\nwrap.SetIntendedName name\nwrap.SetAuth auth\nSet "+d+" = wrap\nEnd Function";
try{window.execScript(k,"vbscript")
}catch(n){return false
}}return true
},setup:function(o,k){if(o===".."){e();
return true
}try{var m=document.getElementById(o);
var n=window[d](o,k);
m.contentWindow.opener=n
}catch(l){return false
}return true
},call:function(k,n,m){try{if(i[k]){i[k].SendMessage(gadgets.json.stringify(m))
}}catch(l){return false
}return true
}}
}()
};;
gadgets.rpctx=gadgets.rpctx||{};
if(!gadgets.rpctx.rmr){gadgets.rpctx.rmr=function(){var h=500;
var f=10;
var i={};
var a=gadgets.util.getUrlParameters()["parent"];
var c;
var j;
function l(q,o,p,n){var r=function(){document.body.appendChild(q);
q.src="about:blank";
if(n){q.onload=function(){m(n)
}
}q.src=o+"#"+p
};
if(document.body){r()
}else{gadgets.util.registerOnLoadHandler(function(){r()
})
}}function d(q){if(typeof i[q]==="object"){return
}var r=document.createElement("iframe");
var o=r.style;
o.position="absolute";
o.top="0px";
o.border="0";
o.opacity="0";
o.width="10px";
o.height="1px";
r.id="rmrtransport-"+q;
r.name=r.id;
var p=gadgets.rpc.getRelayUrl(q);
var n=gadgets.rpc.getOrigin(a);
if(!p){p=n+"/robots.txt"
}i[q]={frame:r,receiveWindow:null,relayUri:p,relayOrigin:n,searchCounter:0,width:10,waiting:true,queue:[],sendId:0,recvId:0,verifySendToken:String(Math.random()),verifyRecvToken:null,originVerified:false};
if(q!==".."){l(r,p,b(q))
}e(q)
}function e(p){var r=null;
i[p].searchCounter++;
try{var o=gadgets.rpc._getTargetWin(p);
if(p===".."){r=o.frames["rmrtransport-"+gadgets.rpc.RPC_ID]
}else{r=o.frames["rmrtransport-.."]
}}catch(q){}var n=false;
if(r){n=g(p,r)
}if(!n){if(i[p].searchCounter>f){return
}window.setTimeout(function(){e(p)
},h)
}}function k(o,q,u,t){var p=null;
if(u!==".."){p=i[".."]
}else{p=i[o]
}if(p){if(q!==gadgets.rpc.ACK){p.queue.push(t)
}if(p.waiting||(p.queue.length===0&&!(q===gadgets.rpc.ACK&&t&&t.ackAlone===true))){return true
}if(p.queue.length>0){p.waiting=true
}var n=p.relayUri+"#"+b(o);
try{p.frame.contentWindow.location=n;
var r=p.width==10?20:10;
p.frame.style.width=r+"px";
p.width=r
}catch(s){return false
}}return true
}function b(o){var p=i[o];
var n={id:p.sendId};
if(p){n.d=Array.prototype.slice.call(p.queue,0);
var q={s:gadgets.rpc.ACK,id:p.recvId};
if(!p.originVerified){q.sendToken=p.verifySendToken
}if(p.verifyRecvToken){q.recvToken=p.verifyRecvToken
}n.d.push(q)
}return gadgets.json.stringify(n)
}function m(y){var v=i[y];
var r=v.receiveWindow.location.hash.substring(1);
var z=gadgets.json.parse(decodeURIComponent(r))||{};
var o=z.d||[];
var p=false;
var u=false;
var w=0;
var n=(v.recvId-z.id);
for(var q=0;
q<o.length;
++q){var t=o[q];
if(t.s===gadgets.rpc.ACK){j(y,true);
v.verifyRecvToken=t.sendToken;
if(!v.originVerified&&t.recvToken&&String(t.recvToken)==String(v.verifySendToken)){v.originVerified=true
}if(v.waiting){u=true
}v.waiting=false;
var s=Math.max(0,t.id-v.sendId);
v.queue.splice(0,s);
v.sendId=Math.max(v.sendId,t.id||0);
continue
}p=true;
if(++w<=n){continue
}++v.recvId;
c(t,v.originVerified?v.relayOrigin:undefined)
}if(p||(u&&v.queue.length>0)){var x=(y==="..")?gadgets.rpc.RPC_ID:"..";
k(y,gadgets.rpc.ACK,x,{ackAlone:p})
}}function g(q,t){var p=i[q];
try{var o=false;
o="document" in t;
if(!o){return false
}o=typeof t.document=="object";
if(!o){return false
}var s=t.location.href;
if(s==="about:blank"){return false
}}catch(n){return false
}p.receiveWindow=t;
function r(){m(q)
}if(typeof t.attachEvent==="undefined"){t.onresize=r
}else{t.attachEvent("onresize",r)
}if(q===".."){l(p.frame,p.relayUri,b(q),q)
}else{m(q)
}return true
}return{getCode:function(){return"rmr"
},isParentVerifiable:function(){return true
},init:function(n,o){c=n;
j=o;
return true
},setup:function(p,n){try{d(p)
}catch(o){gadgets.warn("Caught exception setting up RMR: "+o);
return false
}return true
},call:function(n,p,o){return k(n,o.s,p,o)
}}
}()
};;
gadgets.rpctx=gadgets.rpctx||{};
if(!gadgets.rpctx.ifpc){gadgets.rpctx.ifpc=function(){var h=[];
var e=0;
var d;
var a=2000;
var g={};
function c(m){var k=[];
for(var n=0,l=m.length;
n<l;
++n){k.push(encodeURIComponent(gadgets.json.stringify(m[n])))
}return k.join("&")
}function b(m){var k;
for(var j=h.length-1;
j>=0;
--j){var n=h[j];
try{if(n&&(n.recyclable||n.readyState==="complete")){n.parentNode.removeChild(n);
if(window.ActiveXObject){h[j]=n=null;
h.splice(j,1)
}else{n.recyclable=false;
k=n;
break
}}}catch(l){}}if(!k){k=document.createElement("iframe");
k.style.border=k.style.width=k.style.height="0px";
k.style.visibility="hidden";
k.style.position="absolute";
k.onload=function(){this.recyclable=true
};
h.push(k)
}k.src=m;
window.setTimeout(function(){document.body.appendChild(k)
},0)
}function f(j,l){for(var k=l-1;
k>=0;
--k){if(typeof j[k]==="undefined"){return false
}}return true
}return{getCode:function(){return"ifpc"
},isParentVerifiable:function(){return true
},init:function(i,j){d=j;
d("..",true);
return true
},setup:function(j,i){d(j,true);
return true
},call:function(s,r,q){var l=gadgets.rpc.getRelayUrl(s);
++e;
if(!l){gadgets.warn("No relay file assigned for IFPC");
return false
}var i=null,j=[];
if(q.l){var o=q.a;
i=[l,"#",c([r,e,1,0,c([r,q.s,"","",r].concat(o))])].join("");
j.push(i)
}else{i=[l,"#",s,"&",r,"@",e,"&"].join("");
var t=encodeURIComponent(gadgets.json.stringify(q)),n=a-i.length,p=Math.ceil(t.length/n),m=0,k;
while(t.length>0){k=t.substring(0,n);
t=t.substring(n);
j.push([i,p,"&",m,"&",k].join(""));
m+=1
}}do{b(j.shift())
}while(j.length>0);
return true
},_receiveMessage:function(i,n){var o=i[1],m=parseInt(i[2],10),k=parseInt(i[3],10),l=i[i.length-1],j=m===1;
if(m>1){if(!g[o]){g[o]=[]
}g[o][k]=l;
if(f(g[o],m)){l=g[o].join("");
delete g[o];
j=true
}}if(j){n(gadgets.json.parse(decodeURIComponent(l)))
}}}
}()
};;
if(!window.gadgets||!window.gadgets["rpc"]){gadgets.rpc=function(){var M="__cb";
var S="";
var T="__ack";
var f=500;
var G=10;
var b="|";
var u="callback";
var g="origin";
var r="referer";
var s="legacy__";
var q={};
var W={};
var D={};
var B={};
var z=0;
var l={};
var m={};
var d={};
var n={};
var E={};
var e=null;
var p=null;
var A=(window.top!==window.self);
var v=window.name;
var J=function(){};
var P=0;
var Y=1;
var a=2;
var x=window.console;
var V=x&&x.log&&function(ae){x.log(ae)
}||function(){};
var R=(function(){function ae(af){return function(){V(af+": call ignored")
}
}return{getCode:function(){return"noop"
},isParentVerifiable:function(){return true
},init:ae("init"),setup:ae("setup"),call:ae("call")}
})();
if(gadgets.util){d=gadgets.util.getUrlParameters()
}function K(){if(d.rpctx=="flash"){return gadgets.rpctx.flash
}if(d.rpctx=="rmr"){return gadgets.rpctx.rmr
}var ae=typeof window.postMessage==="function"?gadgets.rpctx.wpm:typeof window.postMessage==="object"?gadgets.rpctx.wpm:window.ActiveXObject?(gadgets.rpctx.flash?gadgets.rpctx.flash:(gadgets.rpctx.nix?gadgets.rpctx.nix:gadgets.rpctx.ifpc)):navigator.userAgent.indexOf("WebKit")>0?gadgets.rpctx.rmr:navigator.product==="Gecko"?gadgets.rpctx.frameElement:gadgets.rpctx.ifpc;
if(!ae){ae=R
}return ae
}function k(aj,ah){if(n[aj]){return
}var af=H;
if(!ah){af=R
}n[aj]=af;
var ae=E[aj]||[];
for(var ag=0;
ag<ae.length;
++ag){var ai=ae[ag];
ai.t=F(aj);
af.call(aj,ai.f,ai)
}E[aj]=[]
}var I=false,U=false;
function N(){if(U){return
}function ae(){I=true
}if(typeof window.addEventListener!="undefined"){window.addEventListener("unload",ae,false)
}else{if(typeof window.attachEvent!="undefined"){window.attachEvent("onunload",ae)
}}U=true
}function j(ae,ai,af,ah,ag){if(!B[ai]||B[ai]!==af){gadgets.error("Invalid gadgets.rpc token. "+B[ai]+" vs "+af);
J(ai,a)
}ag.onunload=function(){if(m[ai]&&!I){J(ai,Y);
gadgets.rpc.removeReceiver(ai)
}};
N();
ah=gadgets.json.parse(decodeURIComponent(ah))
}function Z(ai,af){if(ai&&typeof ai.s==="string"&&typeof ai.f==="string"&&ai.a instanceof Array){if(B[ai.f]){if(B[ai.f]!==ai.t){gadgets.error("Invalid gadgets.rpc token. "+B[ai.f]+" vs "+ai.t);
J(ai.f,a)
}}if(ai.s===T){window.setTimeout(function(){k(ai.f,true)
},0);
return
}if(ai.c){ai[u]=function(aj){var ak=ai.g?s:"";
gadgets.rpc.call(ai.f,ak+M,null,ai.c,aj)
}
}if(af){var ag=t(af);
ai[g]=af;
var ah=ai.r;
if(!ah||t(ah)!=ag){ah=af
}ai[r]=ah
}var ae=(q[ai.s]||q[S]).apply(ai,ai.a);
if(ai.c&&typeof ae!=="undefined"){gadgets.rpc.call(ai.f,M,null,ai.c,ae)
}}}function t(ag){if(!ag){return""
}ag=((ag.split("#"))[0].split("?"))[0];
ag=ag.toLowerCase();
if(ag.indexOf("//")==0){ag=window.location.protocol+ag
}if(ag.indexOf("://")==-1){ag=window.location.protocol+"//"+ag
}var ah=ag.substring(ag.indexOf("://")+3);
var ae=ah.indexOf("/");
if(ae!=-1){ah=ah.substring(0,ae)
}var aj=ag.substring(0,ag.indexOf("://"));
var ai="";
var ak=ah.indexOf(":");
if(ak!=-1){var af=ah.substring(ak+1);
ah=ah.substring(0,ak);
if((aj==="http"&&af!=="80")||(aj==="https"&&af!=="443")){ai=":"+af
}}return aj+"://"+ah+ai
}function C(af,ae){return"/"+af+(ae?b+ae:"")
}function y(ah){if(ah.charAt(0)=="/"){var af=ah.indexOf(b);
var ag=af>0?ah.substring(1,af):ah.substring(1);
var ae=af>0?ah.substring(af+1):null;
return{id:ag,origin:ae}
}else{return null
}}function ad(ag){if(typeof ag==="undefined"||ag===".."){return window.parent
}var af=y(ag);
if(af){return window.top.frames[af.id]
}ag=String(ag);
var ae=window.frames[ag];
if(ae){return ae
}ae=document.getElementById(ag);
if(ae&&ae.contentWindow){return ae.contentWindow
}return null
}function L(ah){var ag=null;
var ae=O(ah);
if(ae){ag=ae
}else{var af=y(ah);
if(af){ag=af.origin
}else{if(ah==".."){ag=d.parent
}else{ag=document.getElementById(ah).src
}}}return t(ag)
}var H=K();
q[S]=function(){V("Unknown RPC service: "+this["s"])
};
q[M]=function(af,ae){var ag=l[af];
if(ag){delete l[af];
ag.call(this,ae)
}};
function X(ag,ae){if(m[ag]===true){return
}if(typeof m[ag]==="undefined"){m[ag]=0
}var af=ad(ag);
if(ag===".."||af!=null){if(H.setup(ag,ae)===true){m[ag]=true;
return
}}if(m[ag]!==true&&m[ag]++<G){window.setTimeout(function(){X(ag,ae)
},f)
}else{n[ag]=R;
m[ag]=true
}}function O(af){var ae=W[af];
if(ae&&ae.substring(0,1)==="/"){if(ae.substring(1,2)==="/"){ae=document.location.protocol+ae
}else{ae=document.location.protocol+"//"+document.location.host+ae
}}return ae
}function ac(af,ae,ag){if(ae&&!/http(s)?:\/\/.+/.test(ae)){if(ae.indexOf("//")==0){ae=window.location.protocol+ae
}else{if(ae.charAt(0)=="/"){ae=window.location.protocol+"//"+window.location.host+ae
}else{if(ae.indexOf("://")==-1){ae=window.location.protocol+"//"+ae
}}}}W[af]=ae;
if(typeof ag!=="undefined"){D[af]=!!ag
}}function F(ae){return B[ae]
}function c(ae,af){af=af||"";
B[ae]=String(af);
X(ae,af)
}function ab(af){var ae=af.passReferrer||"";
var ag=ae.split(":",2);
e=ag[0]||"none";
p=ag[1]||"origin"
}function aa(ae){if(Q(ae)){H=gadgets.rpctx.ifpc||R;
H.init(Z,k)
}}function Q(ae){return String(ae.useLegacyProtocol)==="true"
}function h(af,ae){function ag(aj){var ai=aj&&aj.rpc||{};
ab(ai);
var ah=ai.parentRelayUrl||"";
ah=t(d.parent||ae)+ah;
ac("..",ah,Q(ai));
aa(ai);
c("..",af)
}if(!d.parent&&ae){ag({});
return
}gadgets.config.register("rpc",null,ag)
}function o(af,aj,al){var ai=null;
if(af.charAt(0)!="/"){if(!gadgets.util){return
}ai=document.getElementById(af);
if(!ai){throw new Error("Cannot set up gadgets.rpc receiver with ID: "+af+", element not found.")
}}var ae=ai&&ai.src;
var ag=aj||gadgets.rpc.getOrigin(ae);
ac(af,ag);
var ak=gadgets.util.getUrlParameters(ae);
var ah=al||ak.rpctoken;
c(af,ah)
}function i(ae,ag,ah){if(ae===".."){var af=ah||d.rpctoken||d.ifpctok||"";
h(af,ag)
}else{o(ae,ag,ah)
}}function w(ag){if(e==="bidir"||(e==="c2p"&&ag==="..")||(e==="p2c"&&ag!=="..")){var af=window.location.href;
var ah="?";
if(p==="query"){ah="#"
}else{if(p==="hash"){return af
}}var ae=af.lastIndexOf(ah);
ae=ae===-1?af.length:ae;
return af.substring(0,ae)
}return null
}return{config:function(ae){if(typeof ae.securityCallback==="function"){J=ae.securityCallback
}},register:function(af,ae){if(af===M||af===T){throw new Error("Cannot overwrite callback/ack service")
}if(af===S){throw new Error("Cannot overwrite default service: use registerDefault")
}q[af]=ae
},unregister:function(ae){if(ae===M||ae===T){throw new Error("Cannot delete callback/ack service")
}if(ae===S){throw new Error("Cannot delete default service: use unregisterDefault")
}delete q[ae]
},registerDefault:function(ae){q[S]=ae
},unregisterDefault:function(){delete q[S]
},forceParentVerifiable:function(){if(!H.isParentVerifiable()){H=gadgets.rpctx.ifpc
}},call:function(ae,ag,al,aj){ae=ae||"..";
var ak="..";
if(ae===".."){ak=v
}else{if(ae.charAt(0)=="/"){ak=C(v,gadgets.rpc.getOrigin(window.location.href))
}}++z;
if(al){l[z]=al
}var ai={s:ag,f:ak,c:al?z:0,a:Array.prototype.slice.call(arguments,3),t:B[ae],l:!!D[ae]};
var af=w(ae);
if(af){ai.r=af
}if(ae!==".."&&y(ae)==null&&!document.getElementById(ae)){return
}var ah=n[ae];
if(!ah&&y(ae)!==null){ah=H
}if(ag.indexOf(s)===0){ah=H;
ai.s=ag.substring(s.length);
ai.c=ai.c?ai.c:z
}ai.g=true;
ai.r=ak;
if(!ah){if(!E[ae]){E[ae]=[ai]
}else{E[ae].push(ai)
}return
}if(D[ae]){ah=gadgets.rpctx.ifpc
}if(ah.call(ae,ak,ai)===false){n[ae]=R;
H.call(ae,ak,ai)
}},getRelayUrl:O,setRelayUrl:ac,setAuthToken:c,setupReceiver:i,getAuthToken:F,removeReceiver:function(ae){delete W[ae];
delete D[ae];
delete B[ae];
delete m[ae];
delete n[ae]
},getRelayChannel:function(){return H.getCode()
},receive:function(af,ae){if(af.length>4){H._receiveMessage(af,Z)
}else{j.apply(null,af.concat(ae))
}},receiveSameDomain:function(ae){ae.a=Array.prototype.slice.call(ae.a);
window.setTimeout(function(){Z(ae)
},0)
},getOrigin:t,getTargetOrigin:L,init:function(){if(H.init(Z,k)===false){H=R
}if(A){i("..")
}else{gadgets.config.register("rpc",null,function(af){var ae=af.rpc||{};
ab(ae);
aa(ae)
})
}},_getTargetWin:ad,_parseSiblingId:y,ACK:T,RPC_ID:v||"..",SEC_ERROR_LOAD_TIMEOUT:P,SEC_ERROR_FRAME_PHISH:Y,SEC_ERROR_FORGED_MSG:a}
}();
gadgets.rpc.init()
}else{if(typeof gadgets.rpc=="undefined"||!gadgets.rpc){gadgets.rpc=window.gadgets["rpc"];
gadgets.rpc.config=gadgets.rpc.config;
gadgets.rpc.register=gadgets.rpc.register;
gadgets.rpc.unregister=gadgets.rpc.unregister;
gadgets.rpc.registerDefault=gadgets.rpc.registerDefault;
gadgets.rpc.unregisterDefault=gadgets.rpc.unregisterDefault;
gadgets.rpc.forceParentVerifiable=gadgets.rpc.forceParentVerifiable;
gadgets.rpc.call=gadgets.rpc.call;
gadgets.rpc.getRelayUrl=gadgets.rpc.getRelayUrl;
gadgets.rpc.setRelayUrl=gadgets.rpc.setRelayUrl;
gadgets.rpc.setAuthToken=gadgets.rpc.setAuthToken;
gadgets.rpc.setupReceiver=gadgets.rpc.setupReceiver;
gadgets.rpc.getAuthToken=gadgets.rpc.getAuthToken;
gadgets.rpc.removeReceiver=gadgets.rpc.removeReceiver;
gadgets.rpc.getRelayChannel=gadgets.rpc.getRelayChannel;
gadgets.rpc.receive=gadgets.rpc.receive;
gadgets.rpc.receiveSameDomain=gadgets.rpc.receiveSameDomain;
gadgets.rpc.getOrigin=gadgets.rpc.getOrigin;
gadgets.rpc.getTargetOrigin=gadgets.rpc.getTargetOrigin;
gadgets.rpc._getTargetWin=gadgets.rpc._getTargetWin;
gadgets.rpc._parseSiblingId=gadgets.rpc._parseSiblingId
}};;

/* [end] feature=rpc */
gadgets.config.init({"rpc":{"commSwf":"//xpc.googleusercontent.com/gadgets/xpc.swf","passReferrer":"p2c:query","parentRelayUrl":"/rpc_relay.html"}});
(function(){var j=window['___jsl']=window['___jsl']||{};j['l']=(j['l']||[]).concat(['rpc','core.util']);})();(function(){var j=window['___jsl']=window['___jsl']||{};if(j['c']){j['c']();delete j['c'];}})();var friendconnect_serverBase = "http://www.google.com";var friendconnect_loginUrl = "https://www.google.com/accounts";var friendconnect_gadgetPrefix = "http://www-a-fc-opensocial.googleusercontent.com/gadgets";
var friendconnect_serverVersion = "0.1-1991c764_e9d3234c_6ca83f67_bd79f910_13a3b3c1.7";
var friendconnect_imageUrl = "http://www.google.com/friendconnect/scs/images";
var friendconnect_lightbox = true;
function fca(a){throw a;}var fcb=void 0,fcc=!0,fcd=null,fce=!1,fcf=gadgets,fcg=Error,fch=friendconnect_serverBase,fci=encodeURIComponent,fcaa=parseInt,fcj=String,fck=window,fcba=setTimeout,fcca=Object,fcl=document,fcm=Array,fcn=Math;function fcda(a,b){return a.length=b}function fcea(a,b){return a.className=b}function fco(a,b){return a.width=b}function fcp(a,b){return a.innerHTML=b}function fcq(a,b){return a.height=b}
var fcr="appendChild",fcs="push",fcfa="toString",fct="length",fcga="propertyIsEnumerable",fcha="stringify",fc="prototype",fcia="test",fcu="width",fcv="round",fcw="slice",fcx="replace",fcy="document",fcja="data",fcz="split",fcA="getElementById",fcB="charAt",fcC="location",fcD="getUrlParameters",fcE="indexOf",fcka="Dialog",fcF="style",fcG="body",fcla="left",fcH="call",fcI="match",fcJ="options",fcma="random",fcK="createElement",fcna="json",fcoa="addEventListener",fcL="setAttribute",fcpa="href",fcqa=
"substring",fcra="getContentElement",fcM="util",fcsa="maxHeight",fcta="type",fcN="apply",fcua="auth",fcva="reset",fcwa="getSecurityToken",fcxa="bind",fcO="name",fcya="display",fcP="height",fcQ="register",fcR="join",fcza="unshift",fcAa="toLowerCase",fcBa="right",goog=goog||{},fcS=this,fcDa=function(a,b,c){a=a[fcz](".");c=c||fcS;!(a[0]in c)&&c.execScript&&c.execScript("var "+a[0]);for(var d;a[fct]&&(d=a.shift());)!a[fct]&&fcCa(b)?c[d]=b:c=c[d]?c[d]:c[d]={}},fcEa=function(a){var b=typeof a;if("object"==
b)if(a){if(a instanceof fcm)return"array";if(a instanceof fcca)return b;var c=fcca[fc][fcfa][fcH](a);if("[object Window]"==c)return"object";if("[object Array]"==c||"number"==typeof a[fct]&&"undefined"!=typeof a.splice&&"undefined"!=typeof a[fcga]&&!a[fcga]("splice"))return"array";if("[object Function]"==c||"undefined"!=typeof a[fcH]&&"undefined"!=typeof a[fcga]&&!a[fcga]("call"))return"function"}else return"null";else if("function"==b&&"undefined"==typeof a[fcH])return"object";return b},fcCa=function(a){return a!==
fcb},fcFa=function(a){var b=fcEa(a);return"array"==b||"object"==b&&"number"==typeof a[fct]},fcT=function(a){return"string"==typeof a},fcGa=function(a){var b=typeof a;return"object"==b&&a!=fcd||"function"==b};fcn.floor(2147483648*fcn[fcma]())[fcfa](36);
var fcU=function(a){var b=fcEa(a);if("object"==b||"array"==b){if(a.clone)return a.clone();var b="array"==b?[]:{},c;for(c in a)b[c]=fcU(a[c]);return b}return a},fcHa=function(a,b,c){return a[fcH][fcN](a[fcxa],arguments)},fcIa=function(a,b,c){a||fca(fcg());if(2<arguments[fct]){var d=fcm[fc][fcw][fcH](arguments,2);return function(){var c=fcm[fc][fcw][fcH](arguments);fcm[fc][fcza][fcN](c,d);return a[fcN](b,c)}}return function(){return a[fcN](b,arguments)}},fcV=function(a,b,c){fcV=Function[fc][fcxa]&&
-1!=Function[fc][fcxa][fcfa]()[fcE]("native code")?fcHa:fcIa;return fcV[fcN](fcd,arguments)},fcJa=function(a,b){var c=fcm[fc][fcw][fcH](arguments,1);return function(){var b=fcm[fc][fcw][fcH](arguments);b[fcza][fcN](b,c);return a[fcN](this,b)}},fcKa=function(a,b){for(var c in b)a[c]=b[c]},fcLa=Date.now||function(){return+new Date},fcW=function(a,b,c){fcDa(a,b,c)},fcX=function(a,b){function c(){}c.prototype=b[fc];a.Qc=b[fc];a.prototype=new c;a[fc].constructor=a};
Function[fc].bind=Function[fc][fcxa]||function(a,b){if(1<arguments[fct]){var c=fcm[fc][fcw][fcH](arguments,1);c[fcza](this,a);return fcV[fcN](fcd,c)}return fcV(this,a)};SHA1=function(){this.c=fcm(5);this.da=fcm(64);this.Hc=fcm(80);this.ua=fcm(64);this.ua[0]=128;for(var a=1;64>a;++a)this.ua[a]=0;this[fcva]()};SHA1[fc].reset=function(){this.c[0]=1732584193;this.c[1]=4023233417;this.c[2]=2562383102;this.c[3]=271733878;this.c[4]=3285377520;this.J=this.A=0};SHA1[fc].za=function(a,b){return(a<<b|a>>>32-b)&4294967295};
SHA1[fc].M=function(a){for(var b=this.Hc,c=0;64>c;c+=4){var d=a[c]<<24|a[c+1]<<16|a[c+2]<<8|a[c+3];b[c/4]=d}for(c=16;80>c;++c)b[c]=this.za(b[c-3]^b[c-8]^b[c-14]^b[c-16],1);for(var a=this.c[0],d=this.c[1],e=this.c[2],g=this.c[3],j=this.c[4],i,k,c=0;80>c;++c)40>c?20>c?(i=g^d&(e^g),k=1518500249):(i=d^e^g,k=1859775393):60>c?(i=d&e|g&(d|e),k=2400959708):(i=d^e^g,k=3395469782),i=this.za(a,5)+i+j+k+b[c]&4294967295,j=g,g=e,e=this.za(d,30),d=a,a=i;this.c[0]=this.c[0]+a&4294967295;this.c[1]=this.c[1]+d&4294967295;
this.c[2]=this.c[2]+e&4294967295;this.c[3]=this.c[3]+g&4294967295;this.c[4]=this.c[4]+j&4294967295};SHA1[fc].update=function(a,b){b||(b=a[fct]);var c=0;if(0==this.A)for(;c+64<b;)this.M(a[fcw](c,c+64)),c+=64,this.J+=64;for(;c<b;)if(this.da[this.A++]=a[c++],++this.J,64==this.A){this.A=0;for(this.M(this.da);c+64<b;)this.M(a[fcw](c,c+64)),c+=64,this.J+=64}};
SHA1[fc].digest=function(){var a=fcm(20),b=8*this.J;56>this.A?this.update(this.ua,56-this.A):this.update(this.ua,64-(this.A-56));for(var c=63;56<=c;--c)this.da[c]=b&255,b>>>=8;this.M(this.da);for(c=b=0;5>c;++c)for(var d=24;0<=d;d-=8)a[b++]=this.c[c]>>d&255;return a};G_HMAC=function(a,b,c){(!a||"object"!=typeof a||!a[fcva]||!a.update||!a.digest)&&fca(fcg("Invalid hasher object. Hasher unspecified or does not implement expected interface."));b.constructor!=fcm&&fca(fcg("Invalid key."));c&&"number"!=typeof c&&fca(fcg("Invalid block size."));this.k=a;this.ca=c||16;this.Wb=fcm(this.ca);this.Vb=fcm(this.ca);b[fct]>this.ca&&(this.k.update(b),b=this.k.digest());for(c=0;c<this.ca;c++)a=c<b[fct]?b[c]:0,this.Wb[c]=a^G_HMAC.OPAD,this.Vb[c]=a^G_HMAC.IPAD};G_HMAC.OPAD=92;
G_HMAC.IPAD=54;G_HMAC[fc].reset=function(){this.k[fcva]();this.k.update(this.Vb)};G_HMAC[fc].update=function(a){a.constructor!=fcm&&fca(fcg("Invalid data. Data must be an array."));this.k.update(a)};G_HMAC[fc].digest=function(){var a=this.k.digest();this.k[fcva]();this.k.update(this.Wb);this.k.update(a);return this.k.digest()};G_HMAC[fc].Ib=function(a){this[fcva]();this.update(a);return this.digest()};var fcMa=function(a,b){for(var c=1;c<arguments[fct];c++)var d=fcj(arguments[c])[fcx](/\$/g,"$$$$"),a=a[fcx](/\%s/,d);return a},fcNa=function(a,b){var c=fcj(a)[fcAa](),d=fcj(b)[fcAa]();return c<d?-1:c==d?0:1},fcTa=function(a,b){if(b)return a[fcx](fcOa,"&amp;")[fcx](fcPa,"&lt;")[fcx](fcQa,"&gt;")[fcx](fcRa,"&quot;");if(!fcSa[fcia](a))return a;-1!=a[fcE]("&")&&(a=a[fcx](fcOa,"&amp;"));-1!=a[fcE]("<")&&(a=a[fcx](fcPa,"&lt;"));-1!=a[fcE](">")&&(a=a[fcx](fcQa,"&gt;"));-1!=a[fcE]('"')&&(a=a[fcx](fcRa,"&quot;"));
return a},fcOa=/&/g,fcPa=/</g,fcQa=/>/g,fcRa=/\"/g,fcSa=/[&<>\"]/,fcUa=function(a,b){for(var c=0,d=fcj(a)[fcx](/^[\s\xa0]+|[\s\xa0]+$/g,"")[fcz]("."),e=fcj(b)[fcx](/^[\s\xa0]+|[\s\xa0]+$/g,"")[fcz]("."),g=fcn.max(d[fct],e[fct]),j=0;0==c&&j<g;j++){var i=d[j]||"",k=e[j]||"",l=RegExp("(\\d*)(\\D*)","g"),h=RegExp("(\\d*)(\\D*)","g");do{var f=l.exec(i)||["","",""],m=h.exec(k)||["","",""];if(0==f[0][fct]&&0==m[0][fct])break;var c=0==f[1][fct]?0:fcaa(f[1],10),p=0==m[1][fct]?0:fcaa(m[1],10),c=(c<p?-1:c>p?
1:0)||((0==f[2][fct])<(0==m[2][fct])?-1:(0==f[2][fct])>(0==m[2][fct])?1:0)||(f[2]<m[2]?-1:f[2]>m[2]?1:0)}while(0==c)}return c};fcn[fcma]();var fcVa,fcWa,fcXa,fcYa,fcZa=function(){return fcS.navigator?fcS.navigator.userAgent:fcd},fc_a=function(){return fcS.navigator},fc0a=function(){fcYa=fcXa=fcWa=fcVa=fce;var a;if(a=fcZa()){var b=fc_a();fcVa=0==a[fcE]("Opera");fcWa=!fcVa&&-1!=a[fcE]("MSIE");(fcXa=!fcVa&&-1!=a[fcE]("WebKit"))&&a[fcE]("Mobile");fcYa=!fcVa&&!fcXa&&"Gecko"==b.product}};fc0a();
var fc1a=fcVa,fcY=fcWa,fc2a=fcYa,fc3a=fcXa,fc4a=function(){var a=fc_a();return a&&a.platform||""},fc5a=fc4a(),fc6a=function(){fc5a[fcE]("Mac");fc5a[fcE]("Win");fc5a[fcE]("Linux");fc_a()&&(fc_a().appVersion||"")[fcE]("X11")};fc6a();
var fc8a=function(){var a="",b;fc1a&&fcS.opera?(a=fcS.opera.version,a="function"==typeof a?a():a):(fc2a?b=/rv\:([^\);]+)(\)|;)/:fcY?b=/MSIE\s+([^\);]+)(\)|;)/:fc3a&&(b=/WebKit\/(\S+)/),b&&(a=(a=b.exec(fcZa()))?a[1]:""));return fcY&&(b=fc7a(),b>parseFloat(a))?fcj(b):a},fc7a=function(){var a=fcS[fcy];return a?a.documentMode:fcb},fc9a=fc8a(),fc$a={},fcab=function(a){return fc$a[a]||(fc$a[a]=0<=fcUa(fc9a,a))},fcbb={},fccb=function(a){return fcbb[a]||(fcbb[a]=fcY&&!!fcl.documentMode&&fcl.documentMode>=
a)};var fcdb=function(a){fcg.captureStackTrace?fcg.captureStackTrace(this,fcdb):this.stack=fcg().stack||"";a&&(this.message=fcj(a))};fcX(fcdb,fcg);fcdb[fc].name="CustomError";var fceb=function(a,b){b[fcza](a);fcdb[fcH](this,fcMa[fcN](fcd,b));b.shift();this.messagePattern=a};fcX(fceb,fcdb);fceb[fc].name="AssertionError";var fcfb=function(a,b,c,d){var e="Assertion failed";if(c)var e=e+(": "+c),g=d;else a&&(e+=": "+a,g=b);fca(new fceb(""+e,g||[]))},fcgb=function(a,b,c){a||fcfb("",fcd,b,fcm[fc][fcw][fcH](arguments,2));return a};var fchb=fcm[fc],fcib=fchb[fcE]?function(a,b,c){fcgb(a[fct]!=fcd);return fchb[fcE][fcH](a,b,c)}:function(a,b,c){c=c==fcd?0:0>c?fcn.max(0,a[fct]+c):c;if(fcT(a))return!fcT(b)||1!=b[fct]?-1:a[fcE](b,c);for(;c<a[fct];c++)if(c in a&&a[c]===b)return c;return-1},fcjb=fchb.forEach?function(a,b,c){fcgb(a[fct]!=fcd);fchb.forEach[fcH](a,b,c)}:function(a,b,c){for(var d=a[fct],e=fcT(a)?a[fcz](""):a,g=0;g<d;g++)g in e&&b[fcH](c,e[g],g,a)},fckb=function(a,b){return 0<=fcib(a,b)},fclb=function(a){var b=a[fct];if(0<
b){for(var c=fcm(b),d=0;d<b;d++)c[d]=a[d];return c}return[]},fcmb=function(a,b,c){fcgb(a[fct]!=fcd);return 2>=arguments[fct]?fchb[fcw][fcH](a,b):fchb[fcw][fcH](a,b,c)};var fcnb=function(a){for(var b=[],c=0,d=0;d<a[fct];d++){for(var e=a.charCodeAt(d);255<e;)b[c++]=e&255,e>>=8;b[c++]=e}return b};var fcob=fcd,fcpb=fcd,fcqb=fcd,fcrb=fcd,fctb=function(a,b){fcFa(a)||fca(fcg("encodeByteArray takes an array as a parameter"));fcsb();for(var c=b?fcqb:fcob,d=[],e=0;e<a[fct];e+=3){var g=a[e],j=e+1<a[fct],i=j?a[e+1]:0,k=e+2<a[fct],l=k?a[e+2]:0,h=g>>2,g=(g&3)<<4|i>>4,i=(i&15)<<2|l>>6,l=l&63;k||(l=64,j||(i=64));d[fcs](c[h],c[g],c[i],c[l])}return d[fcR]("")},fcub=function(a,b){fcsb();for(var c=b?fcrb:fcpb,d=[],e=0;e<a[fct];){var g=c[a[fcB](e++)],j=e<a[fct],j=j?c[a[fcB](e)]:0;++e;var i=e<a[fct],i=i?c[a[fcB](e)]:
0;++e;var k=e<a[fct],k=k?c[a[fcB](e)]:0;++e;(g==fcd||j==fcd||i==fcd||k==fcd)&&fca(fcg());g=g<<2|j>>4;d[fcs](g);64!=i&&(g=j<<4&240|i>>2,d[fcs](g),64!=k&&(g=i<<6&192|k,d[fcs](g)))}return d},fcsb=function(){if(!fcob){fcob={};fcpb={};fcqb={};fcrb={};for(var a=0;65>a;a++)fcob[a]="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="[fcB](a),fcpb[fcob[a]]=a,fcqb[a]="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_."[fcB](a),fcrb[fcqb[a]]=a}};var fcZ=function(a){this.wb=a},fcvb=/\s*;\s*/;fcZ[fc].Tb=function(a){return!/[;=\s]/[fcia](a)};fcZ[fc].Ub=function(a){return!/[;\r\n]/[fcia](a)};fcZ[fc].set=function(a,b,c,d,e,g){this.Tb(a)||fca(fcg('Invalid cookie name "'+a+'"'));this.Ub(b)||fca(fcg('Invalid cookie value "'+b+'"'));fcCa(c)||(c=-1);e=e?";domain="+e:"";d=d?";path="+d:"";g=g?";secure":"";0>c?c="":(c=0==c?new Date(1970,1,1):new Date(fcLa()+1E3*c),c=";expires="+c.toUTCString());this.vc(a+"="+b+e+d+c+g)};
fcZ[fc].get=function(a,b){for(var c=a+"=",d=this.la(),e=0,g;g=d[e];e++){if(0==g.lastIndexOf(c,0))return g.substr(c[fct]);if(g==a)return""}return b};fcZ[fc].remove=function(a,b,c){var d=this.N(a);this.set(a,"",0,b,c);return d};fcZ[fc].z=function(){return this.ja().keys};fcZ[fc].F=function(){return this.ja().values};fcZ[fc].Q=function(){var a=this.Oa();return!a?0:this.la()[fct]};fcZ[fc].N=function(a){return fcCa(this.get(a))};fcZ[fc].clear=function(){for(var a=this.ja().keys,b=a[fct]-1;0<=b;b--)this.remove(a[b])};
fcZ[fc].vc=function(a){this.wb.cookie=a};fcZ[fc].Oa=function(){return this.wb.cookie};fcZ[fc].la=function(){return(this.Oa()||"")[fcz](fcvb)};fcZ[fc].ja=function(){for(var a=this.la(),b=[],c=[],d,e,g=0;e=a[g];g++)d=e[fcE]("="),-1==d?(b[fcs](""),c[fcs](e)):(b[fcs](e[fcqa](0,d)),c[fcs](e[fcqa](d+1)));return{keys:b,values:c}};var fcwb=new fcZ(fcl);fcwb.MAX_COOKIE_LENGTH=3950;var fc_=function(a,b){fco(this,a);fcq(this,b)};fc_[fc].clone=function(){return new fc_(this[fcu],this[fcP])};fc_[fc].toString=function(){return"("+this[fcu]+" x "+this[fcP]+")"};fc_[fc].ceil=function(){fco(this,fcn.ceil(this[fcu]));fcq(this,fcn.ceil(this[fcP]));return this};fc_[fc].floor=function(){fco(this,fcn.floor(this[fcu]));fcq(this,fcn.floor(this[fcP]));return this};fc_[fc].round=function(){fco(this,fcn[fcv](this[fcu]));fcq(this,fcn[fcv](this[fcP]));return this};
fc_[fc].scale=function(a){fco(this,this[fcu]*a);fcq(this,this[fcP]*a);return this};var fcxb=function(a,b,c){for(var d in a)b[fcH](c,a[d],d,a)},fcyb=function(a){var b=[],c=0,d;for(d in a)b[c++]=a[d];return b},fczb=function(a){var b=[],c=0,d;for(d in a)b[c++]=d;return b},fcAb="constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" "),fcBb=function(a,b){for(var c,d,e=1;e<arguments[fct];e++){d=arguments[e];for(c in d)a[c]=d[c];for(var g=0;g<fcAb[fct];g++)c=fcAb[g],fcca[fc].hasOwnProperty[fcH](d,c)&&(a[c]=d[c])}};var fcCb=!fcY||fccb(9);!fc2a&&!fcY||fcY&&fccb(9)||fc2a&&fcab("1.9.1");fcY&&fcab("9");var fcDb=function(a){a=a.className;return fcT(a)&&a[fcI](/\S+/g)||[]},fcFb=function(a,b){var c=fcDb(a),d=fcmb(arguments,1),e=c[fct]+d[fct];fcEb(c,d);fcea(a,c[fcR](" "));return c[fct]==e},fcEb=function(a,b){for(var c=0;c<b[fct];c++)fckb(a,b[c])||a[fcs](b[c])};var fcGb=function(a){return fcT(a)?fcl[fcA](a):a},fcHb=fcGb,fcIb=function(a,b,c,d){a=d||a;b=b&&"*"!=b?b.toUpperCase():"";if(a.querySelectorAll&&a.querySelector&&(b||c))return c=b+(c?"."+c:""),a.querySelectorAll(c);if(c&&a.getElementsByClassName){a=a.getElementsByClassName(c);if(b){for(var d={},e=0,g=0,j;j=a[g];g++)b==j.nodeName&&(d[e++]=j);fcda(d,e);return d}return a}a=a.getElementsByTagName(b||"*");if(c){d={};for(g=e=0;j=a[g];g++)b=j.className,"function"==typeof b[fcz]&&fckb(b[fcz](/\s+/),c)&&(d[e++]=
j);fcda(d,e);return d}return a},fcKb=function(a,b){fcxb(b,function(b,d){"style"==d?a[fcF].cssText=b:"class"==d?fcea(a,b):"for"==d?a.htmlFor=b:d in fcJb?a[fcL](fcJb[d],b):0==d.lastIndexOf("aria-",0)||0==d.lastIndexOf("data-",0)?a[fcL](d,b):a[d]=b})},fcJb={cellpadding:"cellPadding",cellspacing:"cellSpacing",colspan:"colSpan",frameborder:"frameBorder",height:"height",maxlength:"maxLength",role:"role",rowspan:"rowSpan",type:"type",usemap:"useMap",valign:"vAlign",width:"width"},fcLb=function(a){a=a[fcy];
a="CSS1Compat"==a.compatMode?a.documentElement:a[fcG];return new fc_(a.clientWidth,a.clientHeight)},fc0=function(a,b,c){return fcMb(fcl,arguments)},fcMb=function(a,b){var c=b[0],d=b[1];if(!fcCb&&d&&(d[fcO]||d[fcta])){c=["<",c];d[fcO]&&c[fcs](' name="',fcTa(d[fcO]),'"');if(d[fcta]){c[fcs](' type="',fcTa(d[fcta]),'"');var e={};fcBb(e,d);delete e[fcta];d=e}c[fcs](">");c=c[fcR]("")}c=a[fcK](c);d&&(fcT(d)?fcea(c,d):"array"==fcEa(d)?fcFb[fcN](fcd,[c].concat(d)):fcKb(c,d));2<b[fct]&&fcNb(a,c,b,2);return c},
fcNb=function(a,b,c,d){function e(c){c&&b[fcr](fcT(c)?a.createTextNode(c):c)}for(;d<c[fct];d++){var g=c[d];fcFa(g)&&!(fcGa(g)&&0<g.nodeType)?fcjb(fcOb(g)?fclb(g):g,e):e(g)}},fcOb=function(a){if(a&&"number"==typeof a[fct]){if(fcGa(a))return"function"==typeof a.item||"string"==typeof a.item;if("function"==fcEa(a))return"function"==typeof a.item}return fce};var fcPb="StopIteration"in fcS?fcS.StopIteration:fcg("StopIteration"),fcQb=function(){};fcQb[fc].next=function(){fca(fcPb)};fcQb[fc].__iterator__=function(){return this};var fc1=function(a,b){this.i={};this.e=[];var c=arguments[fct];if(1<c){c%2&&fca(fcg("Uneven number of arguments"));for(var d=0;d<c;d+=2)this.set(arguments[d],arguments[d+1])}else a&&this.nb(a)};fc1[fc].r=0;fc1[fc].K=0;fc1[fc].Q=function(){return this.r};fc1[fc].F=function(){this.L();for(var a=[],b=0;b<this.e[fct];b++){var c=this.e[b];a[fcs](this.i[c])}return a};fc1[fc].z=function(){this.L();return this.e.concat()};fc1[fc].N=function(a){return fcRb(this.i,a)};
fc1[fc].clear=function(){this.i={};fcda(this.e,0);this.K=this.r=0};fc1[fc].remove=function(a){return fcRb(this.i,a)?(delete this.i[a],this.r--,this.K++,this.e[fct]>2*this.r&&this.L(),fcc):fce};fc1[fc].L=function(){if(this.r!=this.e[fct]){for(var a=0,b=0;a<this.e[fct];){var c=this.e[a];fcRb(this.i,c)&&(this.e[b++]=c);a++}fcda(this.e,b)}if(this.r!=this.e[fct]){for(var d={},b=a=0;a<this.e[fct];)c=this.e[a],fcRb(d,c)||(this.e[b++]=c,d[c]=1),a++;fcda(this.e,b)}};
fc1[fc].get=function(a,b){return fcRb(this.i,a)?this.i[a]:b};fc1[fc].set=function(a,b){fcRb(this.i,a)||(this.r++,this.e[fcs](a),this.K++);this.i[a]=b};fc1[fc].nb=function(a){var b;a instanceof fc1?(b=a.z(),a=a.F()):(b=fczb(a),a=fcyb(a));for(var c=0;c<b[fct];c++)this.set(b[c],a[c])};fc1[fc].clone=function(){return new fc1(this)};
fc1[fc].__iterator__=function(a){this.L();var b=0,c=this.e,d=this.i,e=this.K,g=this,j=new fcQb;j.next=function(){for(;;){e!=g.K&&fca(fcg("The map has changed since the iterator was created"));b>=c[fct]&&fca(fcPb);var j=c[b++];return a?j:d[j]}};return j};var fcRb=function(a,b){return fcca[fc].hasOwnProperty[fcH](a,b)};var fcTb=function(a,b,c){fcT(b)?fcSb(a,c,b):fcxb(b,fcJa(fcSb,a))},fcSb=function(a,b,c){a[fcF][fcj(c)[fcx](/\-([a-z])/g,function(a,b){return b.toUpperCase()})]=b},fcUb=function(a,b){var c=9==a.nodeType?a:a.ownerDocument||a[fcy];return c.defaultView&&c.defaultView.getComputedStyle&&(c=c.defaultView.getComputedStyle(a,fcd))?c[b]||c.getPropertyValue(b)||"":""},fcVb=function(a){var b=a.getBoundingClientRect();fcY&&(a=a.ownerDocument,b.left=b[fcla]-(a.documentElement.clientLeft+a[fcG].clientLeft),b.top-=
a.documentElement.clientTop+a[fcG].clientTop);return b},fcYb=function(a,b,c){b instanceof fc_?(c=b[fcP],b=b[fcu]):c==fcb&&fca(fcg("missing height argument"));fcWb(a,b);fcXb(a,c)},fcZb=function(a,b){"number"==typeof a&&(a=(b?fcn[fcv](a):a)+"px");return a},fcXb=function(a,b){fcq(a[fcF],fcZb(b,fcc))},fcWb=function(a,b){fco(a[fcF],fcZb(b,fcc))},fc0b=function(a){if("none"!=(fcUb(a,"display")||(a.currentStyle?a.currentStyle[fcya]:fcd)||a[fcF]&&a[fcF][fcya]))return fc_b(a);var b=a[fcF],c=b[fcya],d=b.visibility,
e=b.position;b.visibility="hidden";b.position="absolute";b.display="inline";a=fc_b(a);b.display=c;b.position=e;b.visibility=d;return a},fc_b=function(a){var b=a.offsetWidth,c=a.offsetHeight,d=fc3a&&!b&&!c;return(!fcCa(b)||d)&&a.getBoundingClientRect?(a=fcVb(a),new fc_(a[fcBa]-a[fcla],a.bottom-a.top)):new fc_(b,c)},fc1b=function(a,b){a[fcF].display=b?"":"none"};var fc2b={},fc3b={};var fc4b=function(a,b,c,d){b=b||"800";c=c||"550";d=d||"friendconnect";a=fck.open(a,d,"menubar=no,toolbar=no,dialog=yes,location=yes,alwaysRaised=yes,width="+b+",height="+c+",resizable=yes,scrollbars=1,status=1");fck.focus&&a&&a.focus()},fc5b=function(a,b){var c=fcf[fcM][fcD]().communityId;fcf.rpc[fcH](fcd,"signin",fcd,c,a,b)};fcW("goog.peoplesense.util.openPopup",fc4b);fcW("goog.peoplesense.util.finishSignIn",fc5b);var fc8b=function(a,b){var c=fc6b()+"/friendconnect/invite/friends",d=fci(shindig[fcua][fcwa]());fc7b(c,d,a,b)},fc7b=function(a,b,c,d){a+="?st="+b;c&&(a+="&customMessage="+fci(c));d&&(a+="&customInviteUrl="+fci(d));b=760;fcY&&(b+=25);fc4b(a,fcj(b),"515","friendconnect_invite")};fcW("goog.peoplesense.util.invite",fc8b);fcW("goog.peoplesense.util.inviteFriends",fc7b);var fc9b=function(a){this.url=a};fc9b[fc].l=function(a,b){(0<=this.url[fcE]("?"+a+"=")||0<=this.url[fcE]("&"+a+"="))&&fca(fcg("duplicate: "+a));if(b===fcd||b===fcb)return this;var c=0<=this.url[fcE]("?")?"&":"?";this.url+=c+a+"="+fci(b);return this};fc9b[fc].toString=function(){return this.url};var fc6b=function(){return fck.friendconnect_serverBase},fc$b=function(a,b,c,d,e,g,j){b=b||"800";c=c||"550";d=d||"friendconnect";g=g||fce;fcf.rpc[fcH](fcd,"openLightboxIframe",j,a,shindig[fcua][fcwa](),b,c,d,e,fcd,fcd,fcd,g)},fcac=function(a,b){var c=fcf[fcM][fcD]().psinvite||"",d=new fc9b(fc6b()+"/friendconnect/signin/home");d.l("st",fck.shindig[fcua][fcwa]());d.l("psinvite",c);d.l("iframeId",a);d.l("loginProvider",b);d.l("subscribeOnSignin","1");fc4b(d[fcfa]());return fce},fcbc=function(){var a=
fcf[fcM][fcD]().communityId;fcf.rpc[fcH](fcd,"signout",fcd,a)},fcdc=function(a,b){var c=fc6b()+"/friendconnect/settings/edit?st="+fci(shindig[fcua][fcwa]())+(a?"&iframeId="+fci(a):"");b&&(c=c+"&"+b);fccc(c)},fcec=function(a){a=fc6b()+"/friendconnect/settings/siteProfile?st="+fci(a);fccc(a)},fccc=function(a){var b=800,c=510;fcY&&(b+=25);fc4b(a,fcj(b),fcj(c))},fcfc=function(a,b,c,d){var d=d||2,e=fcd;if("text"==b)e=fc0("div",{"class":"gfc-button-text"},fc0("div",{"class":"gfc-icon"},fc0("a",{href:"javascript:void(0);"},
c))),a[fcr](e);else if("long"==b||"standard"==b)e=1==d?fc0("div",{"class":"gfc-inline-block gfc-primaryactionbutton gfc-button-base"},fc0("div",{"class":"gfc-inline-block gfc-button-base-outer-box"},fc0("div",{"class":"gfc-inline-block gfc-button-base-inner-box"},fc0("div",{"class":"gfc-button-base-pos"},fc0("div",{"class":"gfc-button-base-top-shadow",innerHTML:"&nbsp;"}),fc0("div",{"class":"gfc-button-base-content"},fc0("div",{"class":"gfc-icon"},c)))))):fc0("table",{"class":"gfc-button-base-v2 gfc-button",
cellpadding:"0",cellspacing:"0"},fc0("tbody",{"class":""},fc0("tr",{"class":""},fc0("td",{"class":"gfc-button-base-v2 gfc-button-1"}),fc0("td",{"class":"gfc-button-base-v2 gfc-button-2"},c),fc0("td",{"class":"gfc-button-base-v2 gfc-button-3"})))),a[fcr](e),"standard"==b&&(b=fc0("div",{"class":"gfc-footer-msg"},"with Google Friend Connect"),1==d&&a[fcr](fc0("br")),a[fcr](b));return e},fcgc=function(a,b){a||fca("google.friendconnect.renderSignInButton: missing options");var c=a[fcF]||"standard",d=a.text,
e=a.version;if("standard"==c)d=a.text||"Sign in";else if("text"==c||"long"==c)d=a.text||"Sign in with Friend Connect";var g=a.element;g||((g=a.id)||fca("google.friendconnect.renderSignInButton: options[id] and options[element] == null"),(g=fcHb(g))||fca("google.friendconnect.renderSignInButton: element "+a.id+" not found"));fcp(g,"");c=fcfc(g,c,d,e);fck[fcoa]?c[fcoa]("click",b,fce):c.attachEvent("onclick",b)},fchc=function(a,b){b=b||fcV(fcac,fcd,fcd,fcd,fcd);fcgc(a,b)},fcic=function(a,b){fcf.rpc[fcH](fcd,
"putReloadViewParam",fcd,a,b);var c=fcf.views.getParams();c[a]=b},fcjc=function(a,b){var c=new fc9b("/friendconnect/gadgetshare/friends");c.l("customMessage",a);c.l("customInviteUrl",b);c.l("container","glb");var d=310;fcY&&(d+=25);fc$b(c[fcfa](),fcj(d),"370")};fcW("goog.peoplesense.util.getBaseUrl",fc6b);fcW("goog.peoplesense.util.finishSignIn",fc5b);fcW("goog.peoplesense.util.signout",fcbc);fcW("goog.peoplesense.util.signin",fcac);fcW("goog.peoplesense.util.editSettings",fcdc);
fcW("goog.peoplesense.util.editSSProfile",fcec);fcW("goog.peoplesense.util.setStickyViewParamToken",fcic);fcW("google.friendconnect.renderSignInButton",fchc);fcW("goog.peoplesense.util.share",fcjc);fcW("goog.peoplesense.util.userAgent.IE",fcY);var fckc={},fclc={},fc2=function(a){this.h=new fc1;this.snippetId=a.id;this.site=a.site;var a=a["view-params"],b=a.skin;this.gc=(b?b.POSITION:"top")||"top";this.Ic={allowAnonymousPost:a.allowAnonymousPost||fce,scope:a.scope||"SITE",docId:a.docId||"",features:a.features||"video,comment",startMaximized:"true",disableMinMax:"true",skin:b};this.absoluteBottom=fcY&&!fcab("7");this.fixedIeSizes=fcY;fck[fcoa]?fck[fcoa]("resize",fcV(this.db,this),fce):fck.attachEvent("onresize",fcV(this.db,this));this.sb()};
fc2[fc].sb=function(){this.site||fca(fcg("Must supply site ID."));this.snippetId||fca(fcg("Must supply a snippet ID."))};fc2[fc].b=10;fc2[fc].Da=1;fc2[fc].q="fc-friendbar-";fc2[fc].t=fc2[fc].q+"outer";fc2[fc].gb=fc2[fc].t+"-shadow";fc2[fc].render=function(){fcl.write(this.Ab());var a=fcGb(this.snippetId);fcp(a,this.P())};fc2[fc].Bb=function(){var a=fcGb(this.t);return a=fc0b(a)[fcu]};fc2[fc].db=function(){for(var a=this.h.z(),b=0;b<a[fct];b++)this.sc(a[b]);goog&&(fc2b&&fc3b&&fcmc)&&fcnc("resize")};
fc2[fc].n=function(){return this.gc};fc2[fc].d=function(a){return this.q+"shadow-"+a};fc2[fc].ka=function(a){return this.q+"menus-"+a};fc2[fc].S=function(a){return this.q+a+"Target"};fc2[fc].ha=function(a){return this.q+a+"Drawer"};fc2[fc].Ta=function(){return this.S("")};fc2[fc].Ua=function(){return this.q+"wallpaper"};fc2[fc].Pa=function(){return this.ha("")};
fc2[fc].Ab=function(){var a=fck.friendconnect_imageUrl+"/",b=a+"shadow_tc.png",c=a+"shadow_bc.png",d=a+"shadow_bl.png",e=a+"shadow_tl.png",g=a+"shadow_tr.png",j=a+"shadow_br.png",a=a+"shadow_cr.png",i=function(a,b){return fcY?'filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src="'+a+'", sizingMethod="scale");':"background-image: url("+a+");background-repeat: "+b+"; "},k="position:absolute; top:";"top"!=this.n()&&(k="position:fixed; bottom:",this.absoluteBottom&&(k="position:absolute; bottom:"));
var l=c;"top"!=this.n()&&(l=b);var h=0,f=[];f[h++]='<style type="text/css">';"top"!=this.n()&&this.absoluteBottom&&(f[h++]="html, body {height: 100%; overflow: auto; };");f[h++]="#"+this.t+" {";f[h++]="background:#E0ECFF;";f[h++]="left:0;";f[h++]="height: "+(fcY?"35px;":"36px;");"top"!=this.n()&&this.absoluteBottom&&(f[h++]="margin-right: 20px;");f[h++]="padding:0;";f[h++]=k+" 0;";f[h++]="width:100%;";f[h++]="z-index:5000;";f[h++]="}";f[h++]="#"+this.gb+" {";f[h++]=i(l,"repeat-x");f[h++]="left:0;";
f[h++]="height:"+this.b+"px;";"top"!=this.n()&&this.absoluteBottom&&(f[h++]="margin-right: 20px;");f[h++]="padding:0;";f[h++]=k+(fcY?"35px;":"36px;");f[h++]="width:100%;";f[h++]="z-index:4998;";f[h++]="}";f[h++]="."+this.Pa()+" {";f[h++]="display: block;";f[h++]="padding:0;";f[h++]=k+(fcY?"34px;":"35px;");f[h++]="z-index:4999;";f[h++]="}";f[h++]="."+this.Ua()+" {";f[h++]="background: white;";f[h++]="height: 100%;";f[h++]="margin-right: "+this.b+"px;";f[h++]="}";f[h++]="."+this.Ta()+" {";f[h++]="border: "+
this.Da+"px solid #ccc;";f[h++]="height: 100%;";f[h++]="left: 0;";f[h++]="background-image: url("+fck.friendconnect_imageUrl+"/loading.gif);";f[h++]="background-position: center;";f[h++]="background-repeat: no-repeat;";f[h++]="}";f[h++]="."+this.d("cr")+" {";f[h++]=i(a,"repeat-y");f[h++]="height: 100%;";f[h++]="position:absolute;";f[h++]="right: 0;";f[h++]="top: 0;";f[h++]="width:"+this.b+"px;";f[h++]="}";f[h++]="."+this.d("bl")+" {";f[h++]=i(d,"no-repeat");f[h++]="height: "+this.b+"px;";f[h++]="position:absolute;";
f[h++]="width:"+this.b+"px;";f[h++]="}";f[h++]="."+this.d("tl")+" {";f[h++]=i(e,"no-repeat");f[h++]="height: "+this.b+"px;";f[h++]="position:absolute;";f[h++]="left:0px;";f[h++]="width:"+this.b+"px;";f[h++]="}";f[h++]="."+this.d("bc")+" {";f[h++]=i(c,"repeat-x");f[h++]="height: "+this.b+"px;";f[h++]="left: "+this.b+"px;";f[h++]="position:absolute;";f[h++]="right: "+this.b+"px;";f[h++]="}";f[h++]="."+this.d("tc")+" {";f[h++]=i(b,"repeat-x");f[h++]="height: "+this.b+"px;";f[h++]="left: "+this.b+"px;";
f[h++]="margin-left: "+this.b+"px;";f[h++]="margin-right: "+this.b+"px;";f[h++]="right: "+this.b+"px;";f[h++]="}";f[h++]="."+this.d("br")+" {";f[h++]=i(j,"no-repeat");f[h++]="height: "+this.b+"px;";f[h++]="position:absolute;";f[h++]="right: 0;";f[h++]="width: "+this.b+"px;";f[h++]="}";f[h++]="."+this.d("tr")+" {";f[h++]=i(g,"no-repeat");f[h++]="height: "+this.b+"px;";f[h++]="position:absolute;";f[h++]="right: 0;";f[h++]="top: 0;";f[h++]="width: "+this.b+"px;";f[h++]="}";f[h++]="</style>";return f[fcR]("")};
fc2[fc].P=function(){var a=['<div id="'+this.t+'"></div>','<div id="'+this.gb+'"></div>','<div id="'+this.ka(this.h.Q())+'"></div>'];return a[fcR]("")};fc2[fc].ub=function(a,b,c,d){this.h.N(a)||(b=new fc3(this,a,b,c,d),c=this.h.Q(),d=fcGb(this.ka(c)),fcp(d,b.P()+'<div id="'+this.ka(c+1)+'"></div>'),this.h.set(a,b))};fc2[fc].oa=function(a){(a=this.h.get(a))&&a.drawer&&fc1b(a.drawer,fce)};fc2[fc].ic=function(a){if(a=this.h.get(a))a.rendered=fce};
fc2[fc].refresh=function(){for(var a=this.h.z(),b=0;b<a[fct];b++){var c=a[b];this.oa(c);this.ic(c)}};fc2[fc].cc=function(a){for(var b=this.h.F(),c=0;c<b[fct];c++){var d=b[c];if(d.id==a){d.Fc();break}}};fc2[fc].bc=function(a){for(var b=this.h.F(),c=0;c<b[fct];c++){var d=b[c];if(d.id==a){d.Zb();break}}};fc2[fc].sc=function(a){if((a=this.h.get(a))&&a.drawer&&a.qa())a.fa(),a.La(),a.Ca()};
fc2[fc].Ec=function(a,b){var c=this.h.get(a);if(c){c.drawer||(c.drawer=fcGb(this.ha(c[fcO])),c.target=fcGb(this.S(c[fcO])),c.sha_bc=fcIb(fcl,"div","top"==this.n()?this.d("bc"):this.d("tc"),c.drawer)[0],c.sha_cr=fcIb(fcl,"div",this.d("cr"),c.drawer)[0]);for(var d=this.h.z(),e=0;e<d[fct];e++){var g=d[e];a!==g&&this.oa(g)}c.fa(b);fc1b(c.drawer,fcc);fck.setTimeout(function(){c.Ca();c.La();c.render()},0)}};
var fc3=function(a,b,c,d,e){this.id=-1;this.bar=a;this.name=b;this.constraints=d;this.skin=e||{};fcq(this,this.skin.HEIGHT||"0");this.url=fck.friendconnect_serverBase+c;this.sha_bc=this.target=this.drawer=fcd;this.loaded=this.rendered=fce;this.fa()};
fc3[fc].fa=function(a){fcBb(this.constraints,a||{});fcBb(this.skin,this.constraints);if(this.bar.fixedIeSizes&&this.constraints[fcla]&&this.constraints[fcBa]){var a=this.bar.Bb(),b=this.constraints[fcla],c=this.constraints[fcBa],a=a-(b+c);a%2&&(a-=1,this.skin.right=this.skin[fcBa]+1);fco(this.skin,a);delete this.skin[fcla]}};
fc3[fc].Ca=function(){if(this.drawer){if(this.skin[fcu]){var a=this.bar.Da,b=this.bar.b,c=fcY?2:0;fcYb(this.target,this.skin[fcu],"");fcYb(this.sha_bc,this.skin[fcu]-b+2*a-c,"");this.skin.rightShadow?fcYb(this.drawer,this.skin[fcu]+b+2*a-c,""):fcYb(this.drawer,this.skin[fcu]+2*a-c,"")}this.skin[fcBa]&&(this.drawer[fcF].right=this.skin[fcBa]+0+"px")}};
fc3[fc].La=function(){if(fcY&&this.drawer){var a=fc0b(this.target),b=a[fcu]-this.bar.b,a=a[fcP];0>b&&(b=0);this.sha_bc&&this.sha_bc[fcF]&&fcYb(this.sha_bc,b,"");this.sha_cr&&this.sha_cr[fcF]&&fcYb(this.sha_cr,"",a)}};
fc3[fc].P=function(){var a="display:none;",b="position: relative; ",c="",d="",e="",g="",j=!!this.skin.rightShadow;j||(c+="display: none; ",e+="display: none; ",d+="right: 0px; ",g+="margin-right: 0px; ");for(var i in this.skin){var k=Number(this.skin[i]);j&&0==fcNa(i,"width")&&(k+=this.bar.b);0==fcNa(i,"height")&&(b+=i+": "+k+"px; ");"rightShadow"!=i&&(0==fcNa(i,"height")&&(k+=this.bar.b),0==fcNa(i,"width")&&(k+=2),a+=i+": "+k+"px; ");fcY&&0==fcNa(i,"width")&&(k=j?k-2*this.bar.b:k-this.bar.b,d+=i+
": "+k+"px; ")}fcY&&0<(this[fcP]|0)&&(j=(this[fcP]|0)+2,c+="height: "+j+"px; ");j=0;i=[];i[j++]='<div id="'+this.bar.ha(this[fcO])+'"class="'+this.bar.Pa()+'"style="'+a+'"> ';"bottom"==this.bar.n()&&(i[j++]='<div class="'+this.bar.d("tl")+'"></div> <div class="'+this.bar.d("tc")+'"style="'+d+'"></div> <div class="'+this.bar.d("tr")+'"style="'+e+'"></div> ');i[j++]='<div style="'+b+'"> <div class="'+this.bar.Ua()+'"style="'+g+'"><div id="'+this.bar.S(this[fcO])+'"class="'+this.bar.Ta()+'"></div> <div class="'+
this.bar.d("cr")+'"style="'+c+'"></div> </div> </div> ';"top"==this.bar.n()&&(i[j++]='<div class="'+this.bar.d("bl")+'"></div> <div class="'+this.bar.d("bc")+'"style="'+d+'"></div> <div class="'+this.bar.d("br")+'"style="'+e+'"></div> ');i[j++]="</div> ";return i[fcR]("")};fc3[fc].Fc=function(){this.rendered=this.qa()};fc3[fc].Zb=function(){this.loaded=this.qa()};fc3[fc].qa=function(){return!!this.drawer&&"none"!=this.drawer[fcF][fcya]};
fc3[fc].render=function(){if(this.rendered==fce){var a={};a.url=this.url;a.id=this.bar.S(this[fcO]);a.site=this.bar.site;a["view-params"]=fcU(this.bar.Ic);"profile"==this[fcO]&&(a["view-params"].profileId="VIEWER");this.skin&&fcBb(a["view-params"].skin,this.skin);a["view-params"].menuName=this[fcO];a["view-params"].opaque="true";a["view-params"].menuPosition=this.bar.gc;fcq(a,"1px");fckc&&(fclc&&fc4)&&(this.id=fc4.render(a))}};fcW("google.friendconnect.FriendBar",fc2);var fcoc="0123456789abcdefghijklmnopqrstuv",fcqc=function(a){a=new fcpc(a);a.sa()%5&&fca(fcg());for(var b=[],c=0;0<a.sa();c++)b[c]=fcoc[fcB](a.ec(5));return b[fcR]("")},fcpc=function(a){this.G=this.o=0;this.ea=a};fcpc[fc].sa=function(){return 8*(this.ea[fct]-this.G)-this.o};
fcpc[fc].ec=function(a){var b=0;a>this.sa()&&fca(fcg());if(0<this.o){var b=255>>this.o&this.ea[this.G],c=8-this.o,d=fcn.min(a%8,c),c=c-d,b=b>>c,a=a-d;this.o+=d;8==this.o&&(this.o=0,this.G++)}for(;8<=a;)b<<=8,b|=this.ea[this.G],this.G++,a-=8;0<a&&(b<<=a,b|=this.ea[this.G]>>8-a,this.o=a);return b};var fcrc=(new Date).getTime(),fc5=function(){},fcsc=function(){},fctc=function(){},fcuc=function(){};fcX(fcuc,fctc);var fcvc=function(a){if(a)for(var b in a)a.hasOwnProperty(b)&&(this[b]=a[b]);if(this.viewParams)for(var c in this.viewParams)/^FC_RELOAD_.*$/[fcia](c)&&(this.viewParams[c]=fcd)};fcvc[fc].render=function(a){var b=this;a&&(b.Gc(),this.Db(function(c){fcTb(a,"visibility","hidden");fcp(a,c);b.refresh(a,c);c=function(a){fcTb(a,"visibility","visible")};c=fcJa(c,a);fcba(c,500);b.chrome=a}))};
fcvc[fc].Db=function(a){return this.Jb(a)};var fc6=function(a){fcvc[fcH](this,a);this.W="../../";this.rpcToken=fcj(fcn[fcv](2147483647*fcn[fcma]()))};fcX(fc6,fcvc);fc6[fc].lb="gfc_iframe_";fc6[fc].mb="friendconnect";fc6[fc].Ma="";fc6[fc].tc="rpc_relay.html";fc6[fc].Z=function(a){this.W=a};fc6[fc].Gc=function(){return this.Ma=fcj(fcn[fcv](2147483647*fcn[fcma]()))};fc6[fc].ia=function(){return this.lb+this.Ma+"_"+this.id};
fc6[fc].refresh=function(a,b){var c=fc4.Lc,d,e={},g=fc4.Na(this.communityId),j=g[fcz]("~"),i=fc4.vb;if(i&&1<j[fct]){d=j[2];var j=j[1],k=[this.specUrl,this.communityId,j,i][fcR](":");e.sig=fc4.hash(d,k);e.userId=j;e.dateStamp=i}e.container=this.mb;e.mid=this.id;e.nocache=fc4.fc;e.view=this.aa;e.parent=fc4.U;this.debug&&(e.debug="1");this.specUrl&&(e.url=this.specUrl);this.communityId&&(i=fcf[fcM][fcD]().profileId,e.communityId=this.communityId,(d=fcf[fcM][fcD]().psinvite)&&(e.psinvite=d),i&&(e.profileId=
i));e.caller=fcwc();e.rpctoken=this.rpcToken;i=fce;d="";j=/Version\/3\..*Safari/;if((j=fc3a&&fcZa()[fcI](j))||!fc4.T[this.specUrl]&&this.viewParams)e["view-params"]=fcf[fcna][fcha](this.viewParams),d="?viewParams="+fci(e["view-params"]),i=fcc;this.prefs&&(e.prefs=fcf[fcna][fcha](this.prefs));this.viewParams&&this.sendViewParamsToServer&&(e["view-params"]=fcf[fcna][fcha](this.viewParams));this.locale&&(e.locale=this.locale);this.secureToken&&(e.st=this.secureToken);j=fc4.Sa(this.specUrl);d=j+"ifr"+
d+(this.hashData?"&"+this.hashData:"");1!=fc4.Kc||i||g||this.secureToken?g&&!e.sig&&(e.fcauth=g):e.sig||(c="get");g=this.ia();fcxc(g,d,c,e,a,b,this.rpcToken)};var fc7=function(){this.j={};this.U="http://"+fcl[fcC].host;this.aa="default";this.fc=1;this.Pc=0;this.Mc="US";this.Nc="en";this.Oc=2147483647};fcX(fc7,fcsc);fc7[fc].v=fcvc;fc7[fc].B=new fcuc;fc7[fc].fb=function(a){this.fc=a};fc7[fc].Ka=function(a){this.Kc=a};fc7[fc].Ra=function(a){return"gadget_"+a};fc7[fc].w=function(a){return this.j[this.Ra(a)]};
fc7[fc].O=function(a){return new this.v(a)};fc7[fc].ob=function(a){a.id=this.Kb();this.j[this.Ra(a.id)]=a};fc7[fc].dc=0;fc7[fc].Kb=function(){return this.dc++};var fczc=function(){fc7[fcH](this);this.B=new fcyc};fcX(fczc,fc7);fczc[fc].v=fc6;fczc[fc].Y=function(a){a[fcI](/^http[s]?:\/\//)||(a=fcl[fcC][fcpa][fcI](/^[^?#]+\//)[0]+a);this.U=a};fczc[fc].I=function(a){var b=this.B.Qa(a);a.render(b)};var fcyc=function(){this.zb={}};fcX(fcyc,fctc);
fcyc[fc].pb=function(a,b){this.zb[a]=b;var c=fcl[fcA](b).className;!c&&0==c[fct]&&fcea(fcl[fcA](b),"gadgets-gadget-container")};fcyc[fc].Qa=function(a){return(a=this.zb[a.id])?fcl[fcA](a):fcd};var fc8=function(a){fc6[fcH](this,a);a=a||{};this.aa=a.view||"profile"};fcX(fc8,fc6);fc8[fc].rb="canvas.html";fc8[fc].xb="/friendconnect/embed/";
var fcwc=function(){var a="1"==fcf[fcM][fcD]().canvas||"1"==fcf[fcM][fcD]().embed,b=fcd;a&&(b=fcf[fcM][fcD]().caller);b||(a=fcl[fcC],b=a.search[fcx](/([&?]?)psinvite=[^&]*(&?)/,function(a,b,e){return e?b:""}),b=a.protocol+"//"+a.hostname+(a.port?":"+a.port:"")+a.pathname+b);return b};fc8[fc].Cc=function(a){this.aa=a};fc8[fc].na=function(){return this.aa};fc8[fc].getBodyId=function(){return this.ia()+"_body"};
fc8[fc].Jb=function(a){var b=this.specUrl;b===fcb&&(b="");var b=(fc4.Sa(b)||this.W)+this.tc,c=this.ia();fcf.rpc.setRelayUrl(c,b);b='<div id="'+this.getBodyId()+'"><iframe id="'+c+'" name="'+c;b=0==this[fcP]?b+'" style="width:1px; height:1px;':b+'" style="width:100%;';this.viewParams.opaque&&(b+="background-color:white;");b+='"';b+=' frameborder="0" scrolling="no"';this.viewParams.opaque||(b+=' allowtransparency="true"');b+=this[fcP]?' height="'+this[fcP]+'"':"";b+=this[fcu]?' width="'+this[fcu]+'"':
"";b+="></iframe>";this.showEmbedThis&&(b+='<a href="javascript:void(0);" onclick="google.friendconnect.container.showEmbedDialog(\''+this.divId+"'); return false;\">Embed this</a>");b+="</div>";a(b)};
fc8[fc].Cb=function(){var a=fcwc(),a="canvas=1&caller="+fci(a),b=fcf[fcM][fcD]().psinvite;b&&(a+="&psinvite="+fci(b));a+="&site="+fci(this.communityId);b=fcU(this.viewParams);if(b.skin!=fcd)for(var c="BG_IMAGE BG_COLOR FONT_COLOR BG_POSITION BG_REPEAT ANCHOR_COLOR FONT_FACE BORDER_COLOR CONTENT_BG_COLOR CONTENT_HEADLINE_COLOR CONTENT_LINK_COLOR CONTENT_SECONDARY_TEXT_COLOR CONTENT_SECONDARY_LINK_COLOR CONTENT_TEXT_COLOR ENDCAP_BG_COLOR ENDCAP_LINK_COLOR ENDCAP_TEXT_COLOR CONTENT_VISITED_LINK_COLOR ALTERNATE_BG_COLOR".split(" "),d=
0;d<c[fct];d++)delete b.skin[c[d]];b=fci(fcf[fcna][fcha](b));b=b[fcx]("\\","%5C");return fc4.U+this.rb+"?url="+fci(this.specUrl)+(a?"&"+a:"")+"&view-params="+b};fc8[fc].D=function(a){a=a||fch+this.xb+this.communityId;return this.Eb(a,"embed=1")};fc8[fc].C=function(a){return'<iframe src="'+this.D(a)+'" style="height:500px" scrolling="no" allowtransparency="true" border="0" frameborder="0" ></iframe>'};
fc8[fc].Eb=function(a,b){var c=fci(fcf[fcna][fcha](this.viewParams)),c=c[fcx]("\\","%5C");return a+"?url="+fci(this.specUrl)+(b?"&"+b:"")+"&view-params="+c};fc8[fc].Nb=function(){var a="1"==fcf[fcM][fcD]().canvas||"1"==fcf[fcM][fcD]().embed,b=fcd;a&&((b=fcf[fcM][fcD]().caller)||(b="javascript:history.go(-1)"));return b};fc8[fc].Ob=function(a){var b=fcd;"canvas"==a?b=this.Cb():"profile"==a&&(b=this.Nb());return b};
var fc9=function(){fczc[fcH](this);fcf.rpc[fcQ]("signin",fc5[fc].signin);fcf.rpc[fcQ]("signout",fc5[fc].signout);fcf.rpc[fcQ]("resize_iframe",fc5[fc].eb);fcf.rpc[fcQ]("set_title",fc5[fc].setTitle);fcf.rpc[fcQ]("requestNavigateTo",fc5[fc].cb);fcf.rpc[fcQ]("api_loaded",fc5[fc].Ba);fcf.rpc[fcQ]("createFriendBarMenu",fc5[fc].Ga);fcf.rpc[fcQ]("showFriendBarMenu",fc5[fc].hb);fcf.rpc[fcQ]("hideFriendBarMenu",fc5[fc].Va);fcf.rpc[fcQ]("putReloadViewParam",fc5[fc].Za);fcf.rpc[fcQ]("getViewParams",fc5[fc].Ja);
fcf.rpc[fcQ]("getContainerBaseTime",fc5[fc].Ia);fcf.rpc[fcQ]("openLightboxIframe",fc5[fc].Ya);fcf.rpc[fcQ]("showMemberProfile",fc5[fc].jb);fcf.rpc[fcQ]("closeLightboxIframe",fcV(this.u,this));fcf.rpc[fcQ]("setLightboxIframeTitle",fcV(this.yc,this));fcf.rpc[fcQ]("refreshAndCloseIframeLightbox",fcV(this.hc,this));var a=fcAc;a[fcQ]();a.kb(this,"load",this.Qb);a.kb(this,"start",this.Rb);this.W="../../";this.Y("");this.fb(0);this.Ka(1);this.ra=fcd;this.apiVersion="0.8";this.openSocialSecurityToken=fcd;
this.X="";this.Ha={};this.Yb=fcd;this.Xb=fce;this.vb=this.ac=this.lastIframeLightboxOpenArguments=this.lastLightboxCallback=this.lastLightboxDialog=fcd;this.Lc="post"};fcX(fc9,fczc);fc9[fc].wc=function(a){this.vb=a};fc9[fc].v=fc8;fc9[fc].T={};fc9[fc].Ac=function(a){this.ra=a};fc9[fc].Sa=function(a){var b=fc9[fc].T[a];b||(0!==this.ra[fcE]("http://")?(a=this.tb(a),b=["http://",a,this.ra][fcR]("")):b=this.ra);return b};
fc9[fc].tb=function(a){var b=new SHA1,a=fcnb(a);b.update(a);b=b.digest();return b=fcqc(b)};
var fcBc=function(a,b){var c=b?b:fck.top,d=c.frames;try{if(c.frameElement.id==a)return c}catch(e){}for(c=0;c<d[fct];++c){var g=fcBc(a,d[c]);if(g)return g}return fcd},fcxc=function(a,b,c,d,e,g,j){var i="gfc_load_"+a,b='<html><head><style type="text/css">body {background:transparent;}</style>'+(fcY?'<script type="text/javascript">window.goback=function(){history.go(-1);};setTimeout("goback();", 0);<\/script>':"")+"</head><body><form onsubmit='window.goback=function(){};return false;' style='margin:0;padding:0;' id='"+
i+"' method='"+c+"' ' action='"+fcf[fcM].escapeString(b)+"'>",k;for(k in d)b+="<input type='hidden' name='"+k+"' value='' >";var b=b+"</form></body></html>",c=fcBc(a),l;try{l=c[fcy]||c.contentWindow[fcy]}catch(h){e&&g&&(fcp(e,""),fcp(e,g),c=fcBc(a),l=c[fcy]||c.contentWindow[fcy])}j&&fcf.rpc.setAuthToken(a,j);l.open();l.write(b);l.close();a=l[fcA](i);for(k in d)a[k].value=d[k];if(fcY)a.onsubmit();a.submit()};
fc9[fc].yb=function(){var a=fcf[fcM][fcD]().fcsite,b=fcf[fcM][fcD]().fcprofile;a&&b&&fc4.Aa(b,a)};fc9[fc].xc=function(a,b){this.T[a]=b};fc9[fc].V=function(){var a=/Version\/3\..*Safari/;if(a=fc3a&&fcZa()[fcI](a))fcl[fcC].reload();else{fc4.g!=fcd&&fc4.g.refresh();for(var b in fc4.j)a=fc4.j[b],this.I(a);this.lastIframeLightboxOpenArguments!=fcd&&(b=this.lastIframeLightboxOpenArguments,this.u(),this.ta[fcN](this,b))}};
fc9[fc].Y=function(a){a[fcI](/^http[s]?:\/\//)||(a=a&&0<a[fct]&&"/"==a[fcqa](0,1)?fcl[fcC][fcpa][fcI](/^http[s]?:\/\/[^\/]+\//)[0]+a[fcqa](1):fcl[fcC][fcpa][fcI](/^[^?#]+\//)[0]+a);this.U=a};fc9[fc].ga=function(a){return"fcauth"+a};fc9[fc].ma=function(a){return"fcauth"+a+"-s"};fc9[fc].hash=function(a,b){var c=new SHA1,d=fcub(a,fcc),c=new G_HMAC(c,d,64),d=fcnb(b),c=c.Ib(d);(new Date).getTime();return fctb(c,fcc)};
fc9[fc].Na=function(a){return a=fcwb.get(this.ga(a))||fcwb.get(this.ma(a))||this.Ha[a]||""};fc9[fc].Z=function(a){this.W=a};fc9[fc].Bc=function(a){this.X=a};fc9[fc].O=function(a){a=new this.v(a);a.Z(this.W);return a};fc9[fc].na=function(){return this.aa};fc9[fc].zc=function(a){this.ac=a};var fc$=function(a){return(a=a[fcI](/_([0-9]+)$/))?fcaa(a[1],10):fcd};
fc9[fc].$=function(a,b,c,d,e,g){this.Jc||(this.ba(fck.friendconnect_serverBase+"/friendconnect/styles/container.css?d="+this.X),this.Jc=fcc);var j=fcCc(d);if(this.Yb!=(j?"rtl":"ltr"))this.ba(fck.friendconnect_serverBase+"/friendconnect/styles/lightbox"+(j?"-rtl":"")+".css?d="+this.X),this.Yb=j?"rtl":"ltr";this.Xb||(this.qb(fck.friendconnect_serverBase+"/friendconnect/script/lightbox.js?d="+this.X),this.Xb=fcc);b=b||0;if(goog.ui&&goog.ui[fcka]){this.u();var b=new goog.ui[fcka]("lightbox-dialog",fcc),
i=this;goog.events.listen(b,goog.ui[fcka].EventType.AFTER_HIDE,function(){i.lastLightboxCallback&&i.lastLightboxCallback();i.Fa()});b.setDraggable(fcc);b.setDisposeOnHide(fcc);b.setBackgroundElementOpacity(0.5);b.setButtonSet(new goog.ui[fcka].ButtonSet);this.lastLightboxDialog=b;this.lastLightboxCallback=c||fcd;c=b.getDialogElement();e=e||702;fcTb(c,"width",fcj(e)+"px");g&&fcTb(c,"height",fcj(g)+"px");a(b);b.getDialogElement()[fcF].direction=j?"rtl":"ltr"}else 5>b?(b++,a=fcV(this.$,this,a,b,c,d,
e,g),fcba(a,1E3)):(this.Fa(),fca(fcg("lightbox.js failed to load")))};fc9[fc].u=function(a){var b=this.lastLightboxDialog,c=this.lastLightboxCallback;this.lastLightboxCallback=fcd;b!=fcd&&(this.lastLightboxDialog.dispatchEvent(goog.ui[fcka].EventType.AFTER_HIDE),b.dispose(),c!=fcd&&c(a))};fc9[fc].Fa=function(){this.lastIframeLightboxOpenArguments=this.lastLightboxCallback=this.lastLightboxDialog=fcd};fc9[fc].yc=function(a){this.lastLightboxDialog&&this.lastLightboxDialog.setTitle(a)};
fc9[fc].hc=function(){this.u();this.V()};fc5[fc].cb=function(a,b){var c=fc$(this.f),c=fc4.w(c),d=fcU(c.originalParams);b&&(d["view-params"]=d["view-params"]||{},d["view-params"]=b);d.locale=c.locale;if(c.useLightBoxForCanvas)d.presentation=a,fc4.lastLightboxDialog!=fcd?fc4.u():fc4.ib(d);else if((c=c.Ob(a))&&fcl[fcC][fcpa]!=c)if("1"==fcf[fcM][fcD]().embed)try{fck.parent.location=c}catch(e){fck.top.location=c}else fcl[fcC].href=c};
fc9[fc].ib=function(a,b){var a=a||{},c=a.locale,d=fcCc(c),e=this;this.u();this.$(function(b){var c=fc0("div",{},fc0("div",{id:"gadget-signin",style:"background-color:#ffffff;height:32px;"}),fc0("div",{id:"gadget-lb-canvas",style:"background-color:#ffffff;"}));b.getTitleTextElement()[fcr](fc0("div",{id:"gfc-canvas-title",style:"color:#000000;"}));b[fcra]()[fcr](c);b.setVisible(fcc);var c=fcU(a),i=fcLb(fck),k=fcn[fcv](0.7*i[fcP]),i={BORDER_COLOR:"#cccccc",ENDCAP_BG_COLOR:"#e0ecff",ENDCAP_TEXT_COLOR:"#333333",
ENDCAP_LINK_COLOR:"#0000cc",ALTERNATE_BG_COLOR:"#ffffff",CONTENT_BG_COLOR:"#ffffff",CONTENT_LINK_COLOR:"#0000cc",CONTENT_TEXT_COLOR:"#333333",CONTENT_SECONDARY_LINK_COLOR:"#7777cc",CONTENT_SECONDARY_TEXT_COLOR:"#666666",CONTENT_HEADLINE_COLOR:"#333333"};c.id="gadget-lb-canvas";fcq(c,fcn.min(498,k)+"px");c.maxHeight=k;c.keepMax&&(fcq(c,k),fcTb(b[fcra](),"height",k+35+"px"));c["view-params"]=c["view-params"]||{};c["view-params"].opaque=fcc;c["view-params"].skin=c["view-params"].skin||{};fcKa(c["view-params"].skin,
i);e.render(c);k={id:"gadget-signin",presentation:"canvas"};k.site=c.site;k.titleDivId="gfc-canvas-title";k["view-params"]={};k["view-params"].opaque=fcc;k.keepMax=c.keepMax;c.securityToken&&(k.securityToken=c.securityToken);c=fcU(i);c.ALIGNMENT=d?"left":"right";e.ab(k,c);b.reposition()},fcb,b,c)};fc5[fc].hb=function(a,b){fc4.g!=fcd&&fc4.g.Ec(a,b)};fc5[fc].Va=function(a){fc4.g!=fcd&&fc4.g.oa(a)};
fc5[fc].Ya=function(a,b,c,d,e,g,j,i,k,l){var h=this.f,a=a+(0<=a[fcE]("?")?"&":"?")+"iframeId="+h;fc4.ta(a,b,c,d,e,g,j,i,k,l,this.callback)};
fc9[fc].ta=function(a,b,c,d,e,g,j,i,k,l,h){var f=fcLb(fck);d==fcd&&(d=fcn[fcv](0.7*f[fcP]));c==fcd&&(c=fcn[fcv](0.7*f[fcu]));for(var m=[],f=0;f<arguments[fct]&&10>f;f++)m[fcs](arguments[f]);"/"==!a[0]&&fca(fcg("lightbox iframes must be relative to fc server"));var p=this,n=g?fcU(g):{},r=fcj(fcn[fcv](2147483647*fcn[fcma]())),q="gfc_lbox_iframe_"+r;fcf.rpc.setAuthToken(q,r);b||(b=fc4.openSocialSecurityToken);var s=fc4.openSocialSiteId;fc4.$(function(c){p.lastIframeLightboxOpenArguments=m;var g="st="+
fci(b)+"&parent="+fci(fc4.U)+"&rpctoken="+fci(r);i||(n.iframeId=q,n.iurl=a,a=fch+"/friendconnect/lightbox");var f=d-54;fcq(n,f);var h='<iframe id="'+q,h=h+('" width="100%" height="'+f+'" frameborder="0" scrolling="auto"></iframe>');c.setContent(h);e&&(c.setTitle(e),l&&(f=c.getTitleTextElement(),fcFb(f,"lightbox-dialog-title-small-text")));c.setVisible(fcc);k||(n.fcauth=fc4.Na(s));a+=(0<=a[fcE]("?")?"&":"?")+g+"&communityId="+s;fcxc(q,a,"POST",n,fcd,fcd,fcd)},fcb,h,fcb,c,d)};
fc5[fc].Ja=function(){var a=fc$(this.f),a=fc4.w(a);return a.viewParams};fc5[fc].Ia=function(){return fcrc};fc5[fc].Za=function(a,b){var c=fc$(this.f),c=fc4.w(c);c.viewParams[a]=b};fc9[fc].Qb=function(a,b){fc4.g!=fcd&&fc4.g.bc(b)};fc9[fc].Rb=function(a,b){fc4.g!=fcd&&fc4.g.cc(b)};fc5[fc].Ga=function(a,b,c,d){fc4.g!=fcd&&fc4.g.ub(a,b,c,d)};fc9[fc].I=function(a){var b=this.B.Qa(a);a.render(b);this.B.postProcessGadget&&this.B.postProcessGadget(a)};
fc5[fc].signout=function(a){fc4.$a(fc4.ga(a));fc4.$a(fc4.ma(a));fc4.Ha={};fc4.V();return fce};fc9[fc].$a=function(a){for(var b=fcl[fcC].pathname,b=b[fcz]("/"),c=0;c<b[fct];c++){for(var d=fcm(c+1),e=0;e<c+1;e++)d[e]=b[e];fcwb.remove(a,d[fcR]("/")+"/")}};
fc5[fc].eb=function(a){var b=fcl[fcA](this.f);b&&0<a&&fcq(b[fcF],a+"px");(b=fcl[fcA](this.f+"_body"))&&0<a&&fcq(b[fcF],a+"px");if(b=fc$(this.f)){var c=fc4.w(b);if(c){if((b=fcl[fcA](c.divId))&&0<a)c&&(c[fcsa]&&c[fcsa]<a)&&(a=c[fcsa],b[fcF].overflowY="auto"),fcq(b[fcF],a+"px");!c.keepMax&&("canvas"==c.na()&&fc4.lastLightboxDialog)&&fc4.lastLightboxDialog.reposition();fcTb(c.chrome,"visibility","visible")}}};
fc5[fc].setTitle=function(a){var b=fc$(this.f),b=fc4.w(b);(b=b.titleDivId)&&fcp(fcl[fcA](b),fcf[fcM].escapeString(a))};fc5[fc].signin=function(a,b,c){fcwb.set(fc4.ga(a),b,31104E3,c);fcwb.set(fc4.ma(a),b,-1,c);fc4.Ha[a]=b;fc4.V()};var fcEc=function(a){fcgc(a,fcDc)};fc9[fc].nc=function(a,b){b&&this.m(b,a);var c={};c.url=fch+"/friendconnect/gadgets/members.xml";this.render(this.s(a,c))};
fc9[fc].pc=function(a,b){b&&this.m(b,a);var c={};c.url=fch+"/friendconnect/gadgets/review.xml";c["view-params"]={startMaximized:"true",disableMinMax:"true",features:"review"};this.render(this.s(a,c))};fc9[fc].va=function(a,b){b&&this.m(b,a);var c={};c.url=fch+"/friendconnect/gadgets/wall.xml";c["view-params"]={startMaximized:"true",disableMinMax:"true",features:"comment"};this.render(this.s(a,c))};
fc9[fc].ab=function(a,b){b&&this.m(b,a);var c={};c.url=fch+"/friendconnect/gadgets/signin.xml";fcq(c,32);this.render(this.s(a,c))};fc9[fc].kc=function(a,b){b&&this.m(b,a);a.prefs=a.prefs||{};a.sendViewParamsToServer=fcc;a.prefs.hints=fck.google_hints;var c={};c.url=fch+"/friendconnect/gadgets/ads.xml";fcq(c,90);this.render(this.s(a,c))};
fc9[fc].ya=function(a,b){if(a.id){b&&this.m(b,a);a["view-params"]=a["view-params"]||{};a["view-params"].opaque="true";this.g=new fc2(a);this.g.render();var c={};c.url=fch+"/friendconnect/gadgets/friendbar.xml";a.id=this.g.t;fcq(a,"1");this.render(this.s(a,c))}};fc9[fc].mc=fc9[fc].ya;fc9[fc].xa=function(a,b){a=a||{};a.url=fch+"/friendconnect/gadgets/signin.xml";a.site=a.site||fcf[fcM][fcD]().site;fcq(a,32);this.wa(a,b)};fc9[fc].lc=fc9[fc].xa;fc9[fc].rc=fc9[fc].va;
fc9[fc].m=function(a,b){var c=b["view-params"];c||(c={},b["view-params"]=c);c.skin=a};fc9[fc].s=function(a,b){var c=this.Xa(b,a);if(b["view-params"]){var d=b["view-params"];a["view-params"]&&(d=this.Xa(d,a["view-params"]));c["view-params"]=d}return c};fc9[fc].oc=function(a,b){b&&this.m(b,a);this.render(a)};fc9[fc].Xa=function(a,b){var c={},d;for(d in b)c[d]=b[d];for(d in a)"undefined"==typeof c[d]&&(c[d]=a[d]);return c};
fc9[fc].render=function(a){this.openSocialSiteId=a.site;a["view-params"]=a["view-params"]||{};var b=this.O({divId:a.id,specUrl:a.url,communityId:a.site,height:a[fcP],locale:a.locale||this.ac,secureToken:a.securityToken,titleDivId:a.titleDivId,showEmbedThis:a.showEmbedThis,useLightBoxForCanvas:a.useLightBoxForCanvas||"undefined"==typeof a.useLightBoxForCanvas&&fck.friendconnect_lightbox,viewParams:a["view-params"],prefs:a.prefs,originalParams:a,debug:a.debug,maxHeight:a[fcsa],sendViewParamsToServer:a.sendViewParamsToServer,
keepMax:a.keepMax});a.presentation&&b.Cc(a.presentation);this.ob(b);this.B.pb(b.id,a.id);fcba(function(){fc4.I(b)},0);return b.id};fc9[fc].qc=function(a,b){a=a||{};a.presentation="canvas";this.bb(a,b)};
fc9[fc].bb=function(a,b,c){a=a||{};a.url=fcf[fcM][fcD]().url;a.site=fcf[fcM][fcD]().site||a.site;var d=fcf[fcM][fcD]()["view-params"];d&&(a["view-params"]=fcf[fcna].parse(decodeURIComponent(d)));c&&(a["view-params"]=a["view-params"]||{},a["view-params"].useFixedHeight=fcc,fcq(a["view-params"],c),b=b||{},b.HEIGHT=fcj(c));this.wa(a,b)};fc9[fc].wa=function(a,b){a=a||{};b&&this.m(b,a);"1"==fcf[fcM][fcD]().canvas?a.presentation="canvas":"1"==fcf[fcM][fcD]().embed&&(a.presentation="embed");fc4.render(a)};
fc9[fc].Pb=function(){var a=fcf[fcM][fcD]().caller;a&&fcl[fcC][fcpa]!=a&&8<a[fct]&&("http://"==a.substr(0,7)[fcAa]()||"https://"==a.substr(0,8)[fcAa]())?fcl[fcC].href=a:(a=fcf[fcM][fcD]().site)?fcl[fcC].href=fch+"/friendconnect/directory/site?id="+a:fck.history.go(-1)};fc9[fc].H="";fc9[fc].Lb=function(){return this.H};fc9[fc].uc=function(a){this.apiVersion=a};fc9[fc].ba=function(a){var b=fcl[fcK]("link");b[fcL]("rel","stylesheet");b[fcL]("type","text/css");b[fcL]("href",a);fcl.getElementsByTagName("head")[0][fcr](b)};
fc9[fc].qb=function(a){var b=fcl[fcK]("script");b[fcL]("src",a);b[fcL]("type","text/javascript");fcl.getElementsByTagName("head")[0][fcr](b)};fc9[fc].Ea=function(a){fcl[fcG]?a():fck[fcoa]?fck[fcoa]("load",a,fce):fck.attachEvent("onload",a)};fc9[fc].pa=function(a){a.site||fca("API not loaded, please pass in a 'site'");this.ba(fck.friendconnect_serverBase+"/friendconnect/styles/container.css?d="+this.X);this.openSocialSiteId=a.site;this.apiLoadedCallback=a.onload;this.Ea(fcV(this.Wa,this,a,"fc-opensocial-api"))};
fc9[fc].$b=fc9[fc].pa;fc9[fc].Sb=function(a){var b={};b.site=this.openSocialSiteId;b["view-params"]={txnId:a};this.Wa(b,"gfc-"+a)};fc9[fc].jc=function(a){var b={},c;for(c in this.j){var d=this.j[c];if(d.viewParams&&d.viewParams.txnId==a)break;else b[c]=d}this.j=b;(a=fcl[fcA]("gfc-"+a))&&(a.parentNode&&a.parentNode.removeChild)&&a.parentNode.removeChild(a)};fc9[fc].Fb=function(){return"<Templates xmlns:fc='http://www.google.com/friendconnect/makeThisReal'>  <Namespace prefix='fc' url='http://www.google.com/friendconnect/makeThisReal'/>  <Template tag='fc:signIn'>    <div onAttach='google.friendconnect.renderSignInButton({element: this})'></div>  </Template></Templates>"};
fc9[fc].Mb=function(){return"<Templates xmlns:os='http://ns.opensocial.org/2008/markup'><Namespace prefix='os' url='http://ns.opensocial.org/2008/markup'/><Template tag='os:Name'>  <span if='${!My.person.profileUrl}'>${My.person.displayName}</span>  <a if='${My.person.profileUrl}' href='${My.person.profileUrl}'>      ${My.person.displayName}</a></Template><Template tag='os:Badge'>  <div><img if='${My.person.thumbnailUrl}' src='${My.person.thumbnailUrl}'/>   <os:Name person='${My.person}'/></div></Template><Template tag='os:PeopleSelector'>  <select onchange='google.friendconnect.PeopleSelectorOnChange(this)' name='${My.inputName}'          multiple='${My.multiple}' x-var='${My.var}' x-max='${My.max}'          x-onselect='${My.onselect}'>    <option repeat='${My.group}' value='${Cur.id}' selected='${Cur.id == My.selected}'>        ${Cur.displayName}    </option>  </select></Template></Templates>"};
var fcFc=function(a){var b;if(a.multiple){b=[];for(var c=0;c<a[fcJ][fct];c++)a[fcJ][c].selected&&b[fcs](a[fcJ][c].value);c=a.getAttribute("x-max");try{c*=1}catch(d){c=0}if(c&&b[fct]>c&&a["x-selected"]){b=a["x-selected"];for(c=0;c<a[fcJ][fct];c++){a[fcJ][c].selected=fce;for(var e=0;e<b[fct];e++)if(a[fcJ][c].value==b[e]){a[fcJ][c].selected=fcc;break}}}}else b=a[fcJ][a.selectedIndex].value;a["x-selected"]=b;(c=a.getAttribute("x-var"))&&fck.opensocial[fcja]&&fck.opensocial[fcja].getDataContext().putDataSet(c,
b);if(c=a.getAttribute("x-onselect"))if(fck[c]&&"function"==typeof fck[c])fck[c](b);else a["x-onselect-fn"]?a["x-onselect-fn"][fcN](a):a["x-onselect-fn"]=new Function(c)};
fc9[fc].Wa=function(a,b){fck.opensocial.template.Loader.loadContent(this.Mb());fck.opensocial.template.Loader.loadContent(this.Fb());fck.opensocial[fcja].processDocumentMarkup();var c=fcl[fcK]("div");c.id=b;fcq(c[fcF],"0px");fco(c[fcF],"0px");c[fcF].position="absolute";c[fcF].visibility="hidden";fcl[fcG][fcr](c);var d={};d.url=fch+"/friendconnect/gadgets/osapi-"+this.apiVersion+".xml";fcq(d,0);d.id=c.id;d.site=a.site;d["view-params"]=a["view-params"];this.render(d)};
fc5[fc].Ba=function(){fc4.H=this.f;fc4.openSocialSecurityToken=this.a[0];var a=fc4.openSocialSecurityToken;fck.opensocial[fcja].executeRequests();fck.opensocial.template.process();fc4.apiLoadedCallback&&(a=fcJa(fc4.apiLoadedCallback,a),fcba(a,0))};fc9[fc].R=function(a){var b=fcd,c;for(c in this.j)if(this.j[c].divId==a){b=this.j[c];break}return b};fc9[fc].D=function(a,b){var c=this.R(a),d=fcd;c&&(d=c.D(b));return d};fc9[fc].C=function(a,b){var c=this.R(a),d=fcd;c&&(d=c.C(b));return d};
fc9[fc].Dc=function(a,b){this.$(function(c){var d=fcl.createTextNode("Copy & paste this code into your site.");c[fcra]()[fcr](d);c[fcra]()[fcr](fcl[fcK]("br"));var d=fc4.C(a,b),e=fcl[fcK]("textarea");fcp(e,d);e[fcL]("style","width:500px;");c[fcra]()[fcr](e);c.setVisible(fcc)})};var fcGc="ar dv fa iw he ku pa sd tk ug ur yi".split(" "),fcCc=function(a){var b=fce;a?(a=a[fcz]("_")[0],b=fckb(fcGc,a)):b=(a=fcUb(fcl[fcG],"direction"))&&"rtl"==a;return b};
fc5[fc].jb=function(a,b){var c=0,d=fcd;try{var e=fc$(this.f),g=fc4.w(e),d=g.secureToken,c=g.communityId}catch(j){}b&&(c=b);fc4.Aa(a,c,this.callback,d)};fc9[fc].Aa=function(a,b,c,d){b=b||this.openSocialSiteId;a={keepMax:fcc,presentation:"canvas",url:fch+"/friendconnect/gadgets/members.xml",site:b,"view-params":{profileId:a}};d&&(a.securityToken=d);this.ib(a,c)};fc9[fc].Hb=function(a){var b=fcd;if((a=this.R(a))&&a.secureToken)b=a.secureToken;return b};
fc9[fc].Gb=function(a){var b=fcd;if((a=this.R(a))&&a.communityId)b=a.communityId;return b};var fcDc=function(a){fc4.H&&fcac(fc4.H,a)},fcHc=function(){fc5[fc].signout(fc4.openSocialSiteId)},fcIc=function(){fcdc(fc4.H)},fcJc=function(a,b){fc8b(a,b)},fcmc=function(){this.p={}};fcmc[fc].register=function(){fcf.rpc[fcQ]("subscribeEventType",fc5[fc].subscribe);fcf.rpc[fcQ]("publishEvent",fc5[fc].publish)};fc5[fc].subscribe=function(a){var b=fcAc;b.p[a]=b.p[a]||[];a=b.p[a];a[a[fct]]={frameId:this}};
fcmc[fc].kb=function(a,b,c){var d=this;d.p[b]=d.p[b]||[];b=d.p[b];b[b[fct]]={container:a,callback:c}};fc5[fc].publish=function(a){var b=fcAc,c=0;this.f&&(c=fc$(this.f));b.p[a]=b.p[a]||[];for(var b=b.p[a],d=0;d<b[fct];d++)b[d].container?b[d].callback[fcH](b[d].container,a,c):fcf.rpc[fcH](b[d].frameId,a,fcd,a,c)};var fcnc=fcV(fc5[fc].publish,new fc5),fcAc=new fcmc,fc4=new fc9;fc4.Ea(fc4.yb);fcW("google.friendconnect.container",fc4);fcW("google.friendconnect.container.refreshGadgets",fc4.V);
fcW("google.friendconnect.container.setParentUrl",fc4.Y);fcW("google.friendconnect.container.setServerBase",fc4.Z);fcW("google.friendconnect.container.setServerVersion",fc4.Bc);fcW("google.friendconnect.container.createGadget",fc4.O);fcW("google.friendconnect.container.openLightboxIframe",fc4.ta);fcW("google.friendconnect.container.renderGadget",fc4.I);fcW("google.friendconnect.container.render",fc4.render);fcW("google.friendconnect.container.goBackToSite",fc4.Pb);
fcW("google.friendconnect.container.renderMembersGadget",fc4.nc);fcW("google.friendconnect.container.renderReviewGadget",fc4.pc);fcW("google.friendconnect.container.renderCommentsGadget",fc4.va);fcW("google.friendconnect.container.renderSignInGadget",fc4.ab);fcW("google.friendconnect.container.renderFriendBar",fc4.mc);fcW("google.friendconnect.container.renderSocialBar",fc4.ya);fcW("google.friendconnect.container.renderCanvasSignInGadget",fc4.lc);
fcW("google.friendconnect.container.renderUrlCanvasGadget",fc4.qc);fcW("google.friendconnect.container.renderEmbedSignInGadget",fc4.xa);fcW("google.friendconnect.container.renderUrlEmbedGadget",fc4.bb);fcW("google.friendconnect.container.renderEmbedGadget",fc4.wa);fcW("google.friendconnect.container.renderWallGadget",fc4.rc);fcW("google.friendconnect.container.renderAdsGadget",fc4.kc);fcW("google.friendconnect.container.renderOpenSocialGadget",fc4.oc);
fcW("google.friendconnect.container.setNoCache",fc4.fb);fcW("google.friendconnect.container.enableProxy",fc4.Ka);fcW("google.friendconnect.container.setDomain",fc4.xc);fcW("google.friendconnect.container.setLockedDomainSuffix",fc4.Ac);fcW("google.friendconnect.container.setLocale",fc4.zc);fcW("google.friendconnect.container.loadOpenSocialApi",fc4.$b);fcW("google.friendconnect.container.initOpenSocialApi",fc4.pa);fcW("google.friendconnect.container.getOpenSocialApiIframeId",fc4.Lb);
fcW("google.friendconnect.container.setApiVersion",fc4.uc);fcW("google.friendconnect.container.getEmbedUrl",fc4.D);fcW("google.friendconnect.container.getEmbedHtml",fc4.C);fcW("google.friendconnect.container.getGadgetSecurityToken",fc4.Hb);fcW("google.friendconnect.container.getGadgetCommunityId",fc4.Gb);fcW("google.friendconnect.container.showEmbedDialog",fc4.Dc);fcW("google.friendconnect.container.showMemberProfile",fc4.Aa);fcW("google.friendconnect.requestSignIn",fcDc);
fcW("google.friendconnect.requestSignOut",fcHc);fcW("google.friendconnect.requestSettings",fcIc);fcW("google.friendconnect.requestInvite",fcJc);fcW("google.friendconnect.renderSignInButton",fcEc);fcW("google.friendconnect.container.invokeOpenSocialApiViaIframe",fc4.Sb);fcW("google.friendconnect.container.removeOpenSocialApiViaIframe",fc4.jc);fcW("google.friendconnect.userAgent.WEBKIT",fc3a);fcW("google.friendconnect.userAgent.IE",fcY);fcW("google.friendconnect.PeopleSelectorOnChange",fcFc);
fcW("google.friendconnect.container.setDateStamp_",fc4.wc);
google.friendconnect.container.setServerBase('http://www-a-fc-opensocial.googleusercontent.com/ps/');google.friendconnect.container.setServerVersion('0.1-1991c764_e9d3234c_6ca83f67_bd79f910_13a3b3c1.7');google.friendconnect.container.setApiVersion('0.8');
google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/activities.xml', 'http://q8j0igk2u2f6kf7jogh6s66md2d7r154-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/ads.xml', 'http://t767uouk8skpac15v8ue0n16regb3m2t-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/ask.xml', 'http://uofgf6lm45rimd9jp6hn983ul6n2en81-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/friendbar.xml', 'http://p7rjrrl49ose4gob99eonlvp0drmce3d-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/content_reveal.xml', 'http://n0mc7q283f00tpk3uifa7sjv4hmrults-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/chat.xml', 'http://4mmefl67as0q39gf1o4pnocubqmdgei0-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/donate.xml', 'http://7v4nflqvq38notsghmcr5a9t6o9g6kn4-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/lamegame.xml', 'http://ffbrstu9puk7qmt45got9mqe5k7ijrs4-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/map.xml', 'http://k0dfp8trn0hi5d2spmo7jmc88n6kr1cc-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/members.xml', 'http://r1rk9np7bpcsfoeekl0khkd2juj27q3o-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/newsletterSubscribe.xml', 'http://k830suiki828goudg9448o6bp0tpu5r3-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/poll.xml', 'http://1ivjd75aqp679vbgohjv74tlhn13rgdu-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/recommended_pages.xml', 'http://iqavu79a908u5vcecp0pq80hhbhkv33b-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/review.xml', 'http://r85jiaoot111joesr8bilfhfeslcc496-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/sample.xml', 'http://785aoao97uti9iqffknjp5e0kn2ljlm4-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/signin.xml', 'http://8fkcem1ves287v3g5lu9gep1j91p3kk1-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/wall.xml', 'http://o29lt44ell30t7ljcdfr8lq2mjakv2co-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setDomain('http://www.google.com/friendconnect/gadgets/osapi-0.8.xml', 'http://mc8tdi0ripmbpds25eboaupdulritrp6-a-fc-opensocial.googleusercontent.com/ps/');

google.friendconnect.container.setLockedDomainSuffix('-a-fc-opensocial.googleusercontent.com/ps/');
window['__ps_loaded__'] = true; 
 }google.friendconnect_ = google.friendconnect;
google.friendconnect.container.setDateStamp_('13f29ea9a0e');