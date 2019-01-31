# Minecraft Mod Loader
Minecraft Mod Loader is an application incorporating the use of HTTP methods and a PHP web-layer to communicate with a MySQL database to install Forge(http://www.minecraftforge.net/forum/) mods from my server and add it directly to the user's .minecraft/mods directory.

# Purpose of creation
The reason for creating this is due to the tedious task of installing mods to Minecraft and sharing the mods for friends to connect and have fun. As described:
- Ensure everyone have the correct version of Forge
    - Server owner have to distribute the correct version of Forge to all users
        - Users will not be able to connect if version is incorrect
    - Ensure forge is being install via client and not server for all users
- Mods need to be downloaded by everyone
    - Server owner (me) is required to download the mods
        - The mods will be need to be zipped and uploaded to a file sharing site or drive
    - Everyone need to install the mod independently 
        - Mod version need to match with the server and other players to ensure stability
        - Mod can be missing or forgotten about during the installation process
- Accessing the .minecraft/mod folder
    - Press Windows key + R to open "Run" window
    - Type "%appdata%\.minecraft\mods" and access the mod folder
    - Drag and drop all the mods into the folder
        - Some user might have the mods scattered in the download designation cause delays
        
# Solution
Minecraft Mod Loader reduce the amount of work and point of errors tremendously.
- Ensure everyone have the correct version of Forge
    - Ensure forge is being install via client and not server for all users
- Mods need to be downloaded by everyone
    - Server owner (me) install and upload require mods to web-server
        - Installation into "%appdata%\.minecraft\mods" is done automatically on execution
