
package viewBook;

import javax.swing.JPanel;

abstract public class AbstractViewMode {

    /* returneaza un JPanel pentru vizualizarea cartii in format Author */
    abstract JPanel authorPanel();

    /* returneaza un JPanel pentru vizualizarea cartii in format Publish */
    abstract JPanel publishPanel();
}
