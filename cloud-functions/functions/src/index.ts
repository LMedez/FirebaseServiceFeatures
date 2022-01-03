import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

admin.initializeApp({
    credential: admin.credential.applicationDefault()
})

// the id will be modified be getting the user Authentication id,
// for praticing reasons i use the id of the unique document in firestore


export const sendMessageOnSubscribeToTopic = functions.firestore.document("users/dHqwQqswQ9nGh3nocz80")
    .onUpdate((change, context) => {
        // If we set `/users/marie/incoming_messages/134` to {body: "Hello"} then
        // context.params.userId == "marie";
        // context.params.messageCollectionId == "incoming_messages";
        // context.params.messageId == "134";
        // ... and ...
        // change.after.data() == {body: "Hello"}

        const document = change.after.data()
        const token = document.notificationToken.token

        const payload = {
            notification: {
                title: "Thanks for subscribing!",
                body: `You are now subscibed to topic ${document.topics[0]}`
            }
        }
        admin.messaging().sendToDevice(token, payload).then(value => {
            functions.logger.log(value)
        }).catch(error => {
            functions.logger.log(error)
        })
    });

