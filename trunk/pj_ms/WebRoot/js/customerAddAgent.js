function init(){
			if(document.all.isagentrepair.checked == true){
				document.all.customername.disabled= true;
		 		document.all.customer.disabled= true;
		 		document.all.agentid.disabled = false;
		 		document.all.agent.disabled = false;
			}else{
				document.all.customername.disabled = false;
		 		document.all.customer.disabled= false;
		 		document.all.agentid.disabled = true;
		 		document.all.agent.disabled = true;
			}
		}
		/**
		 * chageCustomer
		 *
		 */
		 function chageCustomer() {
		 	var result = document.all.isagentrepair.checked;
		 	if(result == true){
		 		document.all.customername.disabled= true;
		 		document.all.customer.disabled= true;
		 		document.all.agentid.disabled = false;
		 		document.all.agent.disabled = false;
		 	}else{
		 		document.all.customername.disabled = false;
		 		document.all.customer.disabled= false;
		 		document.all.agentid.disabled = true;
		 		document.all.agent.disabled = true;
		 	}
		 }
		 
function showCstOrAgt(isAgent){
	if(isAgent == 1){
		document.all.customerText.removeNode(1);
	}else{
		document.all.agentText.removeNode(1);
	}
}