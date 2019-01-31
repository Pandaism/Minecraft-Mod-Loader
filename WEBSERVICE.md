# Introduction
To reduce points of conflict, the web-service was to create to allow users to retrieve mods while giving developer(s) and server owner(s) the ablitity to upload the require mods.

### Uploading mods
The process of uploading mods to the web-service is very easy.
1. key: The key is provided to all developer(s) and server owner(s)
2. action: The methods used to perform certain tasks on the web-service
	1. get: Get the list of required mods
	2. post: Post a list of required mods
3. json: The json that will be uploaded to the webservice
	
### Error handling
- ERROR_EMPTY_KEY: There was no key provided
- ERROR_INVALID_KEY: The key provided does not match any users
- ERROR_EMPTY_ACTION: There was no action provided
- ERROR_INVALID_ACTION: The action provided does not match any users
- ERROR_EMPTY_JSON: There was no json provided
- ERROR_INVALID_JSON_FORMAT: The format of the provide json is invalid