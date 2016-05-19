package eu.sffi.webandpaper.client.ui;
 
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
 
/**
 * List item widget courtesy of https://turbomanage.wordpress.com/2010/02/11/writing-plain-html-in-gwt/
 * @author https://turbomanage.wordpress.com/2010/02/11/writing-plain-html-in-gwt/
 *
 */
public class ListItemWidget extends SimplePanel
{
    public ListItemWidget()
    {
        super((Element) Document.get().createLIElement().cast());
    }
 
    public ListItemWidget(String s)
    {
        this();
        getElement().setInnerText(s);
    }
 
    public ListItemWidget(Widget w)
    {
        this();
        this.add(w);
    }
}