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
							if(!empty($_GET['json'])) {
								$json = $_GET['json'];

								if(validateJSON($json)) {
									$json = $_GET['json'];
									$database->postModList($json);
									print($json);
								} else {
									print('ERROR_INVALID_JSON_FORMAT');
								}
							} else {
								print('ERROR_EMPTY_JSON');
							}
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

	function validateJSON($json) {
		return json_decode($json);
	}
?>