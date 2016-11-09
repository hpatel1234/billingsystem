<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<c:url value="/assets/js/lib/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/lib/jquery.dataTables.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/css/jquery.dataTables.css"/>">
<script type="text/javascript" language="javascript" class="init">
	$(document).ready(function() {
		
		$.ajax({
			url : "http://localhost:8080/billing-system/bills/",
			type : "POST",
			dataType :"json",
			success : function(response) {
				var status = response['status'];
				if (status == 'SUCCESS') {
					$('#billnumber').val(response['data']['billNumber']);
					$('#actualCost').val(response['data']['actualCost']);
					$('#totalTax').val(response['data']['totalTax']);
					$('#totalPrice').val(response['data']['totalPrice']);
					var items = response['data']['items'];
					recreateBill(items);
				} else {
					alert(response['errors'][0]['message']);
				}
				
			}
		});
		
		$('#productCode').on('change', function() {
			var productCode = $('#productCode').val();
			if(productCode != null && productCode.trim() != '') {
				$.ajax({
					url : "http://localhost:8080/billing-system/products/"+productCode,
					type : "GET",
					dataType :"json",
					success : function(response) {
						var status = response['status'];
						$('#productCode').val('');
						if (status == 'SUCCESS') {
							addItemToBill(response['data']);
						} else {
							alert(response['errors'][0]['message']);
						}
						
					}
				});
			}
		});
		
		function addItemToBill(product) {
			var billNumber = Number($('#billnumber').val());
			var request = {};
			request['product'] = product;
			request['commit'] = false;
			request['billNumber'] = billNumber;
			$.ajax({
			    url : "http://localhost:8080/billing-system/bills/"+billNumber,
				type : "POST",
				dataType :"json",
				contentType: "application/json",
				data : JSON.stringify(request),
				success : function(response) {
					var status = response['status'];
					if (status == 'SUCCESS') {
						$('#actualCost').val(response['data']['actualCost']);
						$('#totalTax').val(response['data']['totalTax']);
						$('#totalPrice').val(response['data']['totalPrice']);
						var items = response['data']['items'];
						recreateBill(items);
					} else {
						alert(response['errors'][0]['message']);
					}
					
				}
			});
		}
		
		function recreateBill(items) {
			if($.fn.dataTable.isDataTable('#billGrid')) {
				$('#billGrid').DataTable().destroy();
			}
			
			$('#billGrid').DataTable({
				"data" : items,
				"paging":false,
				"ordering":false,
				"searching":false,
				"info":false,
				"scrollY":"500px",
				"columns" : [ {"data": "name"},
				              {"data": "quantity"},
				              {"data": "unitPrice"},
				              {"data": "tax"},
				              {"data": "price"}]
			});
		}
	} );
</script>
</head>
<body>
	<div class="container">
	<div class="form-group row col-sm-6" style="margin-top: 30px;float:left">
	    <label for="billnumber" class="col-sm-2 col-form-label">Bill :</label>
	    <div class="col-sm-4">
		<input type="text" id="billnumber" class="form-control" autocomplete="off" disabled="disabled"/>
		</div>
	</div>
	<div class="form-group row col-sm-6" style="margin-top: 30px;float:left">
		<div class="col-sm-4" style="float: right">
		<input type="text" id="productCode" placeholder="Enter Product Code" class="form-control col-sm-2" autocomplete="off"/>
		</div>
	</div>
	<table id="billGrid" class="display" cellspacing="0" width="100%">
		<thead>
        		<tr>
            		<th>Item</th>
            		<th>Quantity</th>
            		<th>Unit Price</th>
            		<th>Tax</th>
            		<th>Price</th>
        		</tr>
     	    </thead>
	</table>
	<form class="form-inline">
  		<div class="form-group pull-right">
    		<label for="totalTax">Total Tax :</label>
    		<input type="text" class="form-control" id="totalTax" disabled="disabled">
  		</div>
  		<div class="form-group pull-right">
   			 <label for="totalPrice">Total Price :</label>
    		 <input type="text" class="form-control"  disabled="disabled" id="totalPrice">
  		</div>
  		<div class="form-group pull-right">
   			 <label for="actualCost">Total Bill :</label>
    		 <input type="text" class="form-control"  disabled="disabled" id="actualCost">
  		</div>
	</form>
	</div>
</body>
</html>