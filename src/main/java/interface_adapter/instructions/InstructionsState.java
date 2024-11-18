package interface_adapter.instructions;

public class InstructionsState {

    private String instructionsContent = "";

    // Getter for instructions content
    public String getInstructionsContent() {
        return instructionsContent;
    }

    // Setter for instructions content
    public void setInstructionsContent(String instructionsContent) {
        this.instructionsContent = instructionsContent;
    }

    // Method to append additional instructions content
    public void appendInstructionsContent(String additionalContent) {
        this.instructionsContent += additionalContent;
    }

    // Optional: Add other methods if instructions content needs specific parsing or formatting
}
