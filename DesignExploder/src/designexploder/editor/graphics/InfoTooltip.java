package designexploder.editor.graphics;

import designexploder.util.adt.Pair;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoTooltip extends Figure {

    private List<Label> labels = new ArrayList<Label>(5);

    public InfoTooltip() {
        setLayoutManager(new ToolbarLayout());
    }

    public void setInfo(List<Pair<String, String>> info) {
        Iterator<Label> iterator = labels.iterator();
        for(Pair<String, String> row : info) {
            Label next;
            if (iterator.hasNext()) {
                next = iterator.next();
            } else {
                iterator = EMPTY_ITERATOR;
                labels.add(next = new Label());
                next.setLabelAlignment(Label.LEFT);
                add(next);
            }
            next.setText(row.getFirst() + ": " + row.getSecond());
        }
        while(iterator.hasNext()) {
            remove(iterator.next());
            iterator.remove();
        }
    }

    private static final Iterator<Label> EMPTY_ITERATOR = new Iterator<Label>() {
        public boolean hasNext() {
            return false;
        }

        public Label next() {
            throw new UnsupportedOperationException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    };

}
