package interface_adapter.grid;

import interface_adapter.ViewModel;

/**
 * The View Model for the Grid View.
 */
public class GridViewModel extends ViewModel<GridState> {

    public GridViewModel() {
        super("grid");
        setState(new GridState());
    }

}