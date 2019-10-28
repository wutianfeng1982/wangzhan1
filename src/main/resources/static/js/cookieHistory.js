var addHistory=function(num,id){
    stringCookie=getCookie('history');
    var stringHistory=""!=stringCookie?stringCookie:"{history:[]}";
    var json=new JSON(stringHistory);
    var e="{num:"+num+",id:"+id+"}";
    json['history'].push(e);//添加一个新的记录
    setCookie('history',json.toString(),30);
}
//显示历史记录
var DisplayHistory=function(){
    var p_ele=document.getElementById('history');
     while (p_ele.firstChild) {
      p_ele.removeChild(p_ele.firstChild);
     }

    var historyJSON=getCookie('history');
    var json=new JSON(historyJSON);
    var displayNum=6;
    for(i=json['history'].length-1;i>0;i--){
        addLi(json['history'][i]['num'],json['history'][i]['id'],"history");
        displayNum--;
        if(displayNum==0){break;}
    }
}
//添加一个li元素
var addLi=function(num,id,pid){
    var a=document.createElement('a');
    var href='product.action?pid='+id;
    a.setAttribute('href',href);
    var t=document.createTextNode(num);
    a.appendChild(t);
    var li=document.createElement('li');
    li.appendChild(a);
    document.getElementById(pid).appendChild(li);
}
//添加cookie
var setCookie=function(c_name,value,expiredays)
{
    var exdate=new Date()
    exdate.setDate(exdate.getDate()+expiredays)
    cookieVal=c_name+ "=" +escape(value)+((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
//    alert(cookieVal);
    document.cookie=cookieVal;
}
//获取cookie
function getCookie(c_name)
{
    if (document.cookie.length>0)
      {
      c_start=document.cookie.indexOf(c_name + "=")
      if (c_start!=-1)
        { 
        c_start=c_start + c_name.length+1 
        c_end=document.cookie.indexOf(";",c_start)
        if (c_end==-1) c_end=document.cookie.length
//        document.write(document.cookie.substring(c_start,c_end)+"<br>");
        return unescape(document.cookie.substring(c_start,c_end))
        } 
      }
    return ""
}
function checkCookie()
{
username=getCookie('username')
if (username!=null && username!="")
  {alert('Welcome again '+username+'!')}
else 
  {
  username=prompt('Please enter your name:',"")
  if (username!=null && username!="")
    {
    setCookie('username',username,365)
    }
  }
}
