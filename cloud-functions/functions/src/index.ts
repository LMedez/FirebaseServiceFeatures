import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

export const sendMessageToTopic = functions.firestore.document("users")
    .onWrite((change, context) => {
        // If we set `/users/marie/incoming_messages/134` to {body: "Hello"} then
        // context.params.userId == "marie";
        // context.params.messageCollectionId == "incoming_messages";
        // context.params.messageId == "134";
        // ... and ...
        // change.after.data() == {body: "Hello"}

        const payload = {
            notification: {
                title: "new news!",
                body: "new news for you."
            }
        }
        admin.messaging().sendToTopic("news", payload).then(value => {
            functions.logger.log(value)
        }).catch(error => {
            functions.logger.log(error)
        })
    });




