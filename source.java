package io.sensable.client.adapter;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import io.sensable.client.R;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    /**
     * retrieves a child object from a group's data structure based on the group position
     * and child position index.
     * 
     * @param groupPosition 0-based index of the group within the list of data, which is
     * used to access the corresponding child element within the group's data structure.
     * 
     * @param childPosititon 1-based index of a child element within the parent group,
     * which is being retrieved from the internal data structure of the adapter.
     * 
     * @returns an object representing a specific child element within a group, obtained
     * by accessing the child position of the group's data structure.
     */
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    /**
     * returns the ID of a child object based on its position within a group.
     * 
     * @param groupPosition 0-based position of the group within its parent group.
     * 
     * @param childPosition 0-based index of the child element within its parent group.
     * 
     * @returns a long value representing the position of the child within its group.
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * inflates a layout for a child element based on a provided groupPosition and
     * childPosition, sets the text of a TextView within the layout to the value of a
     * variable `childText`, and returns the converted view.
     * 
     * @param groupPosition 0-based position of the group in the parent view hierarchy.
     * 
     * @param childPosition 0-based index of the child element within its parent group,
     * which is used to identify and retrieve the appropriate child view from the inflated
     * layout in the `getChildView()` method.
     * 
     * @param isLastChild whether the child view being generated is the last child in its
     * group, and is used to style the child view accordingly.
     * 
     * @param convertView view to be modified and inflated with the appropriate layout
     * for the child element.
     * 
     * @param parent ViewGroup that contains the `getChildView()` method and is used to
     * provide context for the inflation of the child view.
     * 
     * @returns a customized View for displaying a child element's text.
     */
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    /**
     * returns the number of child elements within a specific group position in an array
     * list, by iterating through the list and counting the size of each child element
     * within that group.
     * 
     * @param groupPosition 0-based index of a group within the list data, which is used
     * to access the corresponding child elements in the list.
     * 
     * @returns the number of child elements associated with a particular group position.
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    /**
     * retrieves a specific group from a list based on its position. It returns the
     * corresponding group object in the list.
     * 
     * @param groupPosition 0-based index of a specific group within the list data header,
     * which is used to return the corresponding group object in the function `getGroup()`.
     * 
     * @returns an object representing the group at the specified position in the list.
     */
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    /**
     * returns the specified position of a group.
     * 
     * @param groupPosition 0-based position of a group within the list of groups being
     * processed, which is used to retrieve the corresponding group ID.
     * 
     * @returns a long value representing the position of the group.
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * inflates a custom layout for a group in a list, and sets the text of a header label
     * with the title of the group.
     * 
     * @param groupPosition 0-based index of the group in the adapter's dataset, which
     * is used to inflate the appropriate layout for the group and set the header title.
     * 
     * @param isExpanded expansion state of the group, allowing the function to adapt the
     * layout of the group accordingly.
     * 
     * @param convertView view that is to be customized and returned as the result of the
     * `getGroupView()` method.
     * 
     * @param parent ViewGroup that contains the current group being processed, and it
     * is used to determine the layout of the group's view.
     * 
     * @returns a customized view for displaying a list group header, with the title of
     * the group displayed in a bold font.
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * determines if a child element can be selected based on its position within a group.
     * It returns `true` when the child can be selected, and `false` otherwise.
     * 
     * @param groupPosition 0-based position of the group within its parent container or
     * hierarchy, which is used to determine whether a child can be selected.
     * 
     * @param childPosition 0-based index of a specific child element within the parent
     * group's elements, which is used to determine whether the child can be selected or
     * not.
     * 
     * @returns `true`.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
