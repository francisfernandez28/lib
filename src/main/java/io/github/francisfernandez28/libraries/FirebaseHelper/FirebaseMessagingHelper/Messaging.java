package io.github.francisfernandez28.libraries.FirebaseHelper.FirebaseMessagingHelper;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

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

	public Messaging() throws Exception {
		try {
			this.firebaseMessaging = FirebaseMessaging.getInstance();
		} catch (Exception e) {
			throw new Exception("Initialized first the firebaseApp");
		}

	}

	public String sendToForeground(String message, String topic) throws FirebaseMessagingException {
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	public String sendToForegroundAndBackground(String message, String topic, Priority priority,
			String titleNotification) throws FirebaseMessagingException {
		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setSound("default")
				.setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).setNotification(notif).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	public String sendToForegroundAndBackground(String message, String topic, Priority priority,
			String titleNotification, String icon) throws FirebaseMessagingException {
		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setSound("default")
				.setIcon(icon).setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).setNotification(notif).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

	public String sendToForegroundAndBackground(String message, String topic, Priority priority,
			String titleNotification, String icon, String sound) throws FirebaseMessagingException {
		AndroidNotification notif = AndroidNotification.builder().setTitle(titleNotification).setSound("default")
				.setIcon(icon).setSound(sound).setBody(message).build();
		AndroidConfig config = AndroidConfig.builder().putData("Message", message).setNotification(notif).build();
		Message messageFirebase = Message.builder().setTopic(topic).setAndroidConfig(config).build();
		return this.firebaseMessaging.send(messageFirebase);
	}

}
