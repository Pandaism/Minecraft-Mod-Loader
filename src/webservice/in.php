<?php
	require_once('Database.php');

	$key = "";
	$action = "";
	
	if(!empty($_GET['key'])) {
		$database = new Database();
		$database->connect();
	
		$key = $_GET['key'];
		
		if($database->checkKey($key)) {
			if(!empty($_GET['action'])) {
				$action = $_GET['action'];				
				
				switch($action) {
					case 'get':
						print($database->getModList());
						break;
					case 'post':
						if($key != 'guest') {
							
						}
						break;
					default:
						print('ERROR_INVALID_ACTION');
				}
			} else {
				print('ERROR_EMPTY_ACTION');
			}
		} else {
			print('ERROR_INVALID_KEY');
		}
		$database->disconnect();
	} else {
		print('ERROR_EMPTY_KEY');
	}
?>