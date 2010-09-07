/**
 * 重新获取图片
 */
function check_img(validateImage){
	var image = document.getElementById(validateImage);
        image.src="servlet/CreateValideCode?flag="+Math.random();
  }