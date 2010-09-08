/**初始化，在原列表中清除在目标框中已经存在的项
 * 
 */
function init(oSourceSel,oTargetSel){
	 for(var i=0; i<oTargetSel.options.length; i++){
		for(var j=0; j<oSourceSel.options.length; j++){
			if(oTargetSel.options[i].value == oSourceSel.options[j].value){
				oSourceSel.removeChild( oSourceSel.options[j]);
			}
		}
	}
}

function buildTargetstr(oTargetSel,targetstr){
	targetstr.value = '';
 	for(var i=0; i<oTargetSel.options.length; i++){
	 	targetstr.value+=(oTargetSel.options[i].value+",");
	}
}

/**
  * 移动select的部分内容,必须存在value，此函数以value为标准进行移动
  *
  * oSourceSel: 源列表框对象 
  * oTargetSel: 目的列表框对象
  */
 function moveSelected(oSourceSel,oTargetSel)
 {
     //建立存储value和text的缓存数组
     var arrSelValue = new Array();
     var arrSelText = new Array();
     //此数组存贮选中的options，以value来对应
     var arrValueTextRelation = new Array();
     var index = 0;//用来辅助建立缓存数组
     
     //存储源列表框中所有的数据到缓存中，并建立value和选中option的对应关系
     for(var i=0; i<oSourceSel.options.length; i++)
     {
         if(oSourceSel.options[i].selected)
         {
             //存储
             arrSelValue[index] = oSourceSel.options[i].value;
             arrSelText[index] = oSourceSel.options[i].text;
             //建立value和选中option的对应关系
             arrValueTextRelation[arrSelValue[index]] = oSourceSel.options[i];
             index ++;
         }
     }
     
     //增加缓存的数据到目的列表框中，并删除源列表框中的对应项
     for(var i=0; i<arrSelText.length; i++)  
     {
         //增加
         var oOption = document.createElement("option");
         oOption.text = arrSelText[i];
         oOption.value = arrSelValue[i];
         oTargetSel.add(oOption);
         //删除源列表框中的对应项
         oSourceSel.removeChild(arrValueTextRelation[arrSelValue[i]]);
     }
 }