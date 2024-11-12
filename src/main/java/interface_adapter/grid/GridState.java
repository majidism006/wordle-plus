package interface_adapter.grid;

public class GridState {

    private String[][] gridContent = new String[6][5];
    // private String gridError = "";

    // Getter for grid content
    public String[][] getGridContent() {
        return gridContent;
    }

    // Getter for a specific cell's content
    public String getCellContent(int row, int col) {
        return gridContent[row][col];
    }

    // Setter for a specific cell's content
    public void setCellContent(int row, int col, String letter) {
        this.gridContent[row][col] = letter;
    }

    public String getCell(int row, int col) {
        return null;
    }

    // Getter for grid error (When needed)

    // public String getGridError() {
      //  return gridError;
    //}

    // Setter for grid error (When needed)
    // public void setGridError(String gridError) {
       // this.gridError = gridError;
    //}
}
