package io.github.francisfernandez28.libraries.FirebaseHelper.FirebaseMessagingHelper;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import io.github.francisfernandez28.libraries.FirebaseHelper.Exceptions.FirebaseAppNotInitialized;

public class Messaging {

	private FirebaseMessaging firebaseMessaging;

	public enum Priority {
		HIGH(AndroidConfig.Priority.HIGH), NORMAL(AndroidConfig.Priority.NORMAL);

		private final AndroidConfig.Priority priority;

		private Priority(AndroidConfig.Priority priority) {
			this.priority = priority;
		}

		/**
		 * @return the priority
		 */
		public AndroidConfig.Priority getPriority() {
			return priority;
		}

	}

	public Messaging() throws FirebaseAppNotInitialized {
		try {
			this.firebaseMessaging = FirebaseMessaging.getInstance();
		} catch (Exception e) {
			throw new FirebaseAppNotInitialized();
		}

	}

	/**
	 * Send Message in payload
	 * 
	 * @param message
	 *            Message to send
	 * @param topic
	 *            Target topic
	 * @return string id of message sent
	 * @throws FirebaseMessagingException
	 */
	public String sendToForeground(String message, String topic) throws FirebaseMessagingException {
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	/**
	 * Send Message in payload and notification
	 * 
	 * @param message
	 *            Message to send
	 * @param topic
	 *            Target topic
	 * @param priority
	 *            Priority of notification [HIGH,NORMAL]
	 * @param titleNotification
	 *            Title of notification should appear in android notification
	 * @return string id of message sent
	 * @throws FirebaseMessagingException
	 */
	public String sendToForegroundAndBackground(String message, String topic, Priority priority,
			String titleNotification) throws FirebaseMessagingException {
		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setSound("default")
				.setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).setNotification(notif).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	/**
	 * Send Message in payload and notification
	 * 
	 * @param message
	 *            Message to send
	 * @param topic
	 *            Target topic
	 * @param priority
	 *            Priority of notification [HIGH,NORMAL]
	 * @param titleNotification
	 *            Title of notification should appear in android notification
	 * @param icon
	 *            Icon of notification should appear in android notification
	 * @return string id of message sent
	 * @throws FirebaseMessagingException
	 */
	public String sendToForegroundAndBackground(String message, String topic, Priority priority,
			String titleNotification, String icon) throws FirebaseMessagingException {
		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setSound("default")
				.setIcon(icon).setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).setNotification(notif).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	/**
	 * Send message in payload and notification
	 * 
	 * @param message
	 *            Message to send
	 * @param topic
	 *            Target topic
	 * @param priority
	 *            Priority of notification [HIGH,NORMAL]
	 * @param titleNotification
	 *            Title of notification should appear in android notification
	 * @param icon
	 *            Icon of notification should appear in android notification
	 * @param sound
	 *            Sound of notification in android
	 * @return string id of message sent
	 * @throws FirebaseMessagingException
	 */
	public String sendToForegroundAndBackground(String message, String topic, Priority priority,
			String titleNotification, String icon, String sound) throws FirebaseMessagingException {
		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setIcon(icon)
				.setSound(sound).setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).setNotification(notif).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	/**
	 * Send message in payload and notification
	 * 
	 * @param message
	 *            Message to send
	 * @param token
	 *            Target token
	 * @param priority
	 *            Priority of notification [HIGH,NORMAL]
	 * @param titleNotification
	 *            Title of notification should appear in android notification
	 * @param icon
	 *            Icon of notification should appear in android notification
	 * @param sound
	 *            Sound of notification should appear in android notification
	 * @return string id of message sent
	 * @throws FirebaseMessagingException
	 */
	public String sendToTokenForegroundBackground(String message, String token, Priority priority,
			String titleNotification, String icon, String sound) throws FirebaseMessagingException {

		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setIcon(icon)
				.setSound(sound).setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).setNotification(notif).build();
		Message messageFirebase = Message.builder().setToken(token).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	/**
	 * Send message in payload
	 * 
	 * @param message
	 *            Message to send
	 * @param token
	 *            Target token
	 * @param priority
	 *            Priority of notification [HIGH,NORMAL]
	 * @param titleNotification
	 *            Title of notification should appear in android notification
	 * @param icon
	 *            Icon of notification should appear in android notification
	 * @param sound
	 *            Sound of notification should appear in android notification
	 * @return string id of message
	 * @throws FirebaseMessagingException
	 */
	public String sendToTokenForeground(String message, String token, Priority priority, String titleNotification,
			String icon, String sound) throws FirebaseMessagingException {

		AndroidConfig config = AndroidConfig.builder().putData("Message", message).build();
		Message messageFirebase = Message.builder().setToken(token).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	/**
	 * Send message in notification
	 * 
	 * @param message
	 *            Message to send
	 * @param token
	 *            Target token
	 * @param priority
	 *            Priority of notification [HIGH,NORMAL]
	 * @param titleNotification
	 *            Title of notification should appear in android notification
	 * @param icon
	 *            Icon of notification should appear in android notification
	 * @param sound
	 *            Sound of notification should appear in android notification
	 * @return string id of message sent
	 * @throws FirebaseMessagingException
	 */
	public String sendToTokenBackground(String message, String token, Priority priority, String titleNotification,
			String icon, String sound) throws FirebaseMessagingException {

		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setIcon(icon)
				.setSound(sound).setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().setNotification(notif).build();
		Message messageFirebase = Message.builder().setToken(token).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

}
