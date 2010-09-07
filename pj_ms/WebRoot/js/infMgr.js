		/**
		 * 删除一条记录
		 * formName：要提交的表单的名称
		 * listName：记录列的序号字段在struts from中的名称
		 * id：要删除的记录列的序号
		 */
		function del_par1(formID,paraID_1,paraValue_1){
			var delFlag = window.confirm("削除してよろしいですか？");
			if (delFlag) {
				document.getElementById(paraID_1).value = paraValue_1;
				document.getElementById(formID).submit();
			}
		}

				/**
		 * 删除一条记录
		 * formName：要提交的表单的名称
		 * listName：记录列的序号字段在struts from中的名称
		 * id：要删除的记录列的序号
		 */
		function del_par2(formID,paraID_1,paraValue_1,paraID_2,paraValue_2){
			var delFlag = window.confirm("削除してよろしいですか？");
			if (delFlag) {
				document.getElementById(paraID_1).value = paraValue_1;
				document.getElementById(paraID_2).value = paraValue_2;
				document.getElementById(formID).submit();
			}
		}

		/**
		 * 删除一条记录
		 * formName：要提交的表单的名称
		 * listName：记录列的序号字段在struts from中的名称
		 * id：要删除的记录列的序号
		 */
		function del_par3(formID,paraID_1,paraValue_1,paraID_2,paraValue_2,paraID_3,paraValue_3){
			var delFlag = window.confirm("削除してよろしいですか？");
			if (delFlag) {
				document.getElementById(paraID_1).value = paraValue_1;
				document.getElementById(paraID_2).value = paraValue_2;
				document.getElementById(paraID_3).value = paraValue_3;
				document.getElementById(formID).submit();
			}
		}

		function readOpenerWin(){
			opener.location.href = opener.location.href;
			opener.location.reload();
			window.close();
		}

		function changeViewScope(){
			var result = document.all.isViewAll.checked;
			if(result == true){
				document.all.formPageViewScope.value = 'all';
				document.all.formDelViewScope.value = 'all';
				document.all.formViewScope.value = 'all';
			}else{
				var oldScope = document.all.formViewOldScope.value;
				document.all.formPageViewScope.value = oldScope;
				document.all.formDelViewScope.value = oldScope;
				document.all.formViewScope.value = oldScope;
			}
			document.all.formView.submit();
		}

		/*windows.open()打开一个页面居中
		url        --   弹出窗口路径
		sTiltle    --  窗口标题
		sSize     --  窗口型号,可以自己再增加
		*/
		function openwindow(url,sTitle,sSize)
		{
			var nwin;
		    if (url==''){
		      return false;
		    }

		    if (nwin && !nwin.closed){
		       nwin.close();
		    }


		    if (sSize == undefined) {
		       alert("请指定窗口型号!");
		       return false;
		    }
		    else if (sSize == 100) {
		       sWidth  = screen.availWidth;
		       sHeight = screen.availHeight;
		    }

		   else if (sSize == 1) {
		       sWidth  = 280;
		       sHeight = 120;
		    }
		    else if (sSize == 2) {
		       sWidth  = 600;
		       sHeight = 400;
		    }

		    var l = ( screen.availWidth - sWidth ) / 2;
		    var  t = ( screen.availHeight - sHeight ) / 2;
		    nwin = window.open(url,sTitle,'left=' + l +',top=' + t +',width='+sWidth+',height='+sHeight+',scrollbars=yes,resizable=yes');
		    nwin.focus();
		}

		//打开一个窗口居中
	  	function openWin(u, w, h) {
            var l = (screen.width - w) / 2;
            var t = (screen.height - h) / 2;
            var s = 'width=' + w + ', height=' + h + ', top=' + t + ', left=' + l;
            s += ', toolbar=no, scrollbars=no, menubar=no, location=no, resizable=no';
            open(u, 'oWin', s);
      	}

      	/**
      	 * 显示隐藏层
      	 */
      	function showDiv(layer,showContact){
      		layer.style.visibility = (layer.style.visibility != 'visible'?'visible':'hidden');
      		if(layer.style.visibility != 'visible'){
      			showContact.value = '全ての連絡履歴を表示する';
      		}else{
      			showContact.value = '全ての連絡履歴を非表示する';
      		}
      	}

		var fileIndex = 0;
      	/**
      	 * 添加文件上传控件
      	 */
      	function fileAdd(fileSpace){
      		var newNode = document.createElement("p");//创建P标签
      		newNode.innerHTML='<span id="fileSpan'+(fileIndex+=1)+'"> <input type="file" name="file'+fileIndex+'">（写真を選択する）<a onclick="fileDel(document.all.fileSpan'+fileIndex+')"><img src="image/delete.gif"> </a></span>';
      		fileSpace.parentNode.insertBefore(newNode,fileSpace);//在目标标签前面添加元素
      	}
      	
      	function fileDel(fileId){
      		fileId.removeNode(1);
      	}
      	
      	/**
      	 * 
      	 */
      	function imgDel(imgId,imgDelStr,ImgName){
      		var delFlag = window.confirm("削除してよろしいですか？");
			if (delFlag) {
      			imgDelStr.value+=(ImgName+";");
      			imgId.removeNode(1);
			}
      	}