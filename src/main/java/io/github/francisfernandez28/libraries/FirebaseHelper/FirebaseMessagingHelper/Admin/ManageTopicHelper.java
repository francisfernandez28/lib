package io.github.francisfernandez28.libraries.FirebaseHelper.FirebaseMessagingHelper.Admin;

import java.util.List;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.TopicManagementResponse;

public class ManageTopicHelper {

	public TopicManagementResponse subscribeTokensToTopic(List<String> registrationTokens, String topic)
			throws FirebaseMessagingException {
		return FirebaseMessaging.getInstance().subscribeToTopic(registrationTokens, topic);
	}

	public TopicManagementResponse unSubscribeTokensFromTopic(List<String> registrationTokens, String topic)
			throws FirebaseMessagingException {
		return FirebaseMessaging.getInstance().unsubscribeFromTopic(registrationTokens, topic);
	}

}
