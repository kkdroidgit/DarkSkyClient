package paperwrrk.www.darkskyclient.events;

/**
 * Created by Kartik on 27-10-17.
 */

public class ErrorEvent {

    private final String errorMessage;

    public ErrorEvent(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
