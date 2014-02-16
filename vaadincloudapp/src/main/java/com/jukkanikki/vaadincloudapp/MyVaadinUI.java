package com.jukkanikki.vaadincloudapp;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.demo.sampler.ExampleUtil;
import com.vaadin.event.Action;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.CellStyleGenerator;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.jukkanikki.vaadincloudapp.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label("Thank you for clicking"));
            }
        });
        layout.addComponent(button);
        
        Table sample = new Table();
        sample.setSizeFull();
        sample.setSelectable(true);
        sample.setMultiSelect(true);
        sample.setImmediate(true);

        sample.setContainerDataSource(ExampleUtil.getISO3166Container());
        sample.setVisibleColumns(new Object[] {
                ExampleUtil.iso3166_PROPERTY_NAME,
                ExampleUtil.iso3166_PROPERTY_SHORT });

        sample.setColumnReorderingAllowed(true);
        sample.setColumnCollapsingAllowed(true);

        sample.setColumnHeaders(new String[] { "Country", "Code" });

        sample.setColumnAlignment(ExampleUtil.iso3166_PROPERTY_SHORT,
                Align.CENTER);

        sample.setColumnExpandRatio(ExampleUtil.iso3166_PROPERTY_NAME, 1);
        sample.setColumnWidth(ExampleUtil.iso3166_PROPERTY_SHORT, 70);

        sample.setRowHeaderMode(RowHeaderMode.ICON_ONLY);
        sample.setItemIconPropertyId(ExampleUtil.iso3166_PROPERTY_FLAG);

        final Action actionMark = new Action("Mark");
        final Action actionUnmark = new Action("Unmark");

        sample.addActionHandler(new Action.Handler() {
            @Override
            public Action[] getActions(final Object target, final Object sender) {
//                if (markedRows.contains(target)) {
//                    return new Action[] { actionUnmark };
//                } else {
                    return new Action[] { actionMark };
//                }
            }

            @Override
            public void handleAction(final Action action, final Object sender,
                    final Object target) {
//                if (actionMark == action) {
//                    markedRows.add(target);
//                } else if (actionUnmark == action) {
//                    markedRows.remove(target);
//                }
//                sample.markAsDirtyRecursive();
//                Notification.show("Marked rows: " + markedRows,
//                        Type.TRAY_NOTIFICATION);
            }

        });

        sample.setCellStyleGenerator(new CellStyleGenerator() {
            @Override
            public String getStyle(final Table source, final Object itemId,
                    final Object propertyId) {
                String style = null;
//                if (propertyId == null && markedRows.contains(itemId)) {
//                    // no propertyId, styling a row
//                    style = "marked";
//                }
                return style;
            }

        });
 

        sample.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(final ValueChangeEvent event) {
                final String valueString = String.valueOf(event.getProperty()
                        .getValue());
                Notification.show("Value changed:", valueString,
                        Type.TRAY_NOTIFICATION);
            }
        });
        
        layout.addComponent(sample);
        
    }

}
