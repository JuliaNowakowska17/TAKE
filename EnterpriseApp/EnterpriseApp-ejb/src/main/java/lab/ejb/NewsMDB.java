package lab.ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@JMSDestinationDefinition(name = "java:app/jms/NewsQueue",
        interfaceName = "jakarta.jms.Queue", resourceAdapter = "jmsra",
        destinationName = "NewsQueue")

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName =
                "destinationLookup", propertyValue = "java:app/jms/NewsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "jakarta.jms.Queue")
})


public class NewsMDB implements jakarta.jms.MessageListener {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
            if (message instanceof jakarta.jms.TextMessage) {
                jakarta.jms.TextMessage textMessage = (jakarta.jms.TextMessage) message;
                String text = textMessage.getText();
                String[] parts = text.split("\\|", 2);

                if (parts.length == 2) {
                    NewsItem e = new NewsItem();
                    e.setHeading(parts[0]);
                    e.setBody(parts[1]);
                    em.persist(e);
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
