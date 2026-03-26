package lab.backing;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import lab.ejb.NewsItem;
import lab.ejb.NewsItemFacadeLocal;

import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class NewsBean implements Serializable {
    private String headingText;
    private String bodyText;

    public String submitNews()
    {
        sendNewsItem(getHeadingText(), getBodyText());
        setHeadingText("");
        setBodyText("");
        return null;
    }

    @Inject
    private NewsItemFacadeLocal facade;

    @Inject
    private JMSContext context;
    @Resource(lookup="java:app/jms/NewsQueue")
    private jakarta.jms.Queue queue;

    void sendNewsItem(String heading, String body) {
        try {
            jakarta.jms.TextMessage message = context.createTextMessage();
            String newText = heading + "|" + body;
            message.setText(newText);
            context.createProducer().send(queue, message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public List<NewsItem> getNewsItems(){
        return facade.getAllNewsItems();
    }

    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
}
