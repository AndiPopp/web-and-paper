package eu.sffi.webandpaper.client.ui;
 
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * UnorderedListWidget curtesy of https://turbomanage.wordpress.com/2010/02/11/writing-plain-html-in-gwt/
 * @author https://turbomanage.wordpress.com/2010/02/11/writing-plain-html-in-gwt/
 *
 */
public class UnorderedListWidget extends ComplexPanel
{
    public UnorderedListWidget()
    {
        setElement(Document.get().createULElement());
    }
 
    public void setId(String id)
    {
        // Set an attribute common to all tags
        getElement().setId(id);
    }
 
    public void setDir(String dir)
    {
        // Set an attribute specific to this tag
        ((UListElement) getElement().cast()).setDir(dir);
    }
 
    public void add(Widget w)
    {
        // ComplexPanel requires the two-arg add() method
        super.add(w, getElement());
    }
}