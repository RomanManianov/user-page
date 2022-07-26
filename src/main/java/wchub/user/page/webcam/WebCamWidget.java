package wchub.user.page.webcam;

import com.google.gwt.dom.client.Element;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Widget;

public class WebCamWidget extends Widget {

    private final Video video;
    private WebcamStream stream;

    public WebCamWidget() {
        setElement(DOM.createDiv());
        setStyleName("webcam");

        video = Video.createIfSupported();
        if (video != null) {
            getElement().appendChild(video.getElement());
            video.setWidth("100%");
            video.setHeight("100%");
            video.setAutoplay(true);
            requestWebCam();
        } else {
            Element errorDiv = DOM.createDiv();
            errorDiv.setInnerText("Видео не поддерживается!");
            getElement().appendChild(errorDiv);
        }
    }

    public void setClickListener(EventListener listener) {
        if (video != null) {
            DOM.setEventListener(video.getElement(), listener);
        }
    }

    @Override
    protected void onUnload() {
        super.onUnload();
        if (stream != null) {
            stream.stop();
        }
    }

    private native String requestWebCam();
}
