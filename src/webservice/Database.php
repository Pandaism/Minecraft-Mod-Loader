<?php
	include('../databaseinfo.php');

	class Database {
		private $variables = null;
		private $connection = null;
		private $options = [
			PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
			PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
			PDO::ATTR_EMULATE_PREPARES   => false,
		];
		
		function Database() {
			$this->variables = new Variables();
		}
		
		function getModList() {
			$stmt = $this->connection->prepare('SELECT * FROM mods WHERE idmod = ?');
			$stmt->execute([1]);
			$result = $stmt->fetch();
			
			$parsed = str_replace('\\', '',json_encode($result['modlist']));
			$json = substr($parsed, 1, strlen($parsed) - 2);
			
			return $json;
			return $json;
		}
		
		function checkKey($key) {
			$stmt = $this->connection->prepare('SELECT * FROM user WHERE api = ?');
			$stmt->execute([$key]);
		
			return $stmt->rowCount() > 0;
		}
		
		function connect() {
			$host = $this->variables->getHost();
			$db = $this->variables->getDB();
			$user = $this->variables->getUser();
			$pass = $this->variables->getPass();
			
			$this->connection = new PDO(
				"mysql:host=$host;dbname=$db;",
				$user,
				$pass,
				$this->options
			);
		}
		
		function disconnect() {
			$this->connection = null;
		}
	}
?>