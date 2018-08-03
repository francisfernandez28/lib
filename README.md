# lib
libraries


<h1>Welcome</h1>



<h4>Examples</h4>

<h4>FirebaseHelper.FirebaseMessagingHelper</h4>
<pre class="prettyprint">
//Usage

String topic = "yourtopic"
String message = "yourmessage"
Messaging messaging = new Messaging();
String res = messaging.sendToForegroundAndBackground(message, topic);



</pre>

<pre class="prettyprint">
//Messaging Methods
public String sendToForeground(String message, String topic) 
public String sendToForegroundAndBackground(String message, String topic, Priority priority, String titleNotification)
public String sendToForegroundAndBackground(String message, String topic, Priority priority, String titleNotification, String icon)
public String sendToForegroundAndBackground(String message, String topic, Priority priority, String titleNotification, String icon, String sound)

</pre>

Notes:

<b>FirebaseApp</b> must be initialized first before using these libraries

<pre class="prettyprint">
//Your project

FirebaseApp app = FirebaseApp.initializeApp(options); //refer to the firebase documentation

</pre>


Parameter <b>Sound</b> and <b>Icon</b> must be a name of resource file stored inside an android application

Example:



<pre class="prettyprint">

R.mipmap.ic_background //this is the resource name file in an android project

</pre>
<pre class="prettyprint">

String sound =  "ic_background";
String res = messaging.sendToForegroundAndBackground(sendToForegroundAndBackground(String message, String topic, Priority priority, String titleNotification, String icon, sound);


</pre>

Parameter <b>Sound</b> search file name not only on mipmap folders, but in all folders matches the filename.

FirebaseMessagingHelper is from WebApp that send messages to the android users that subscribes to the specific topic. For more info, refer to the firebase messaging documentation in android.

