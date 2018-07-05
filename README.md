# Web-Project
The project consists of saving access credentials and accessing a configurable website in the app

The site is displayed within the webview

Functions for closing / disconnecting / configuring the site and credentials

You can find the main file inside the folder “web view”

# [MainActivity](https://github.com/chefleo/Project-Software-Engineering/blob/master/webview/app/src/main/java/com/example/simonedigiorgio/webview/MainActivity.java)
The MainActivity allows the user to search for the site he wants to search
Transports the string url from the MainActivity to the "second" class which, in the "second" class, is the main webview

# [second](https://github.com/chefleo/Project-Software-Engineering/blob/master/webview/app/src/main/java/com/example/simonedigiorgio/webview/second.java)
Inside the class there is the webview, inside the webview you can save the access credentials and use to access the web, you can also configure the website (disconnect it or close it) and the credentials
