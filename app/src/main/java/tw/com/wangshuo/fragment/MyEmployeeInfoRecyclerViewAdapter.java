package tw.com.wangshuo.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import tw.com.wangshuo.R;
import tw.com.wangshuo.data.Employee;

public class MyEmployeeInfoRecyclerViewAdapter extends RecyclerView.Adapter<MyEmployeeInfoRecyclerViewAdapter.ViewHolder> {

    private final FirebaseDatabase database;
    private List<Employee> employeeList = new ArrayList<>();

    public MyEmployeeInfoRecyclerViewAdapter() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference employeeRef = database.getReference("employee");
        employeeRef.keepSynced(true);
        employeeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    System.out.println("key:" + dataSnapshot.getKey());
                    System.out.println("value:" + dataSnapshot.getValue());
                    Employee employee = dataSnapshot.getValue(Employee.class);
                    System.out.println(employee);
                    employeeList.add(employee);
                    notifyItemInserted(employeeList.size() - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Employee employee = dataSnapshot.getValue(Employee.class);
                int index = employeeList.indexOf(employee);
                if (index > 0) {
                    employeeList.set(index, employee);
                    notifyItemChanged(index);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Employee employee = dataSnapshot.getValue(Employee.class);
                int index = employeeList.indexOf(employee);
                if (index > 0) {
                    employeeList.remove(index);
                    notifyItemRemoved(index);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_employeeinfo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = employeeList.get(position);
        holder.mIdView.setText(holder.mItem.getName());
        holder.mContentView.setText(holder.mItem.getPhone());
        holder.mView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Employee mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
