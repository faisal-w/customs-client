package com.equilibrium.kiriman.tools;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;


/**
 * Created by faisalw on 3/22/17.
 */
@Component
public class DateTimeAdapter extends XmlAdapter<String,Date>{

    //private static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy/MM/dd hh:mm:ss");
    private static final ThreadLocal<DateTimeFormatter> DATE_FORMAT_TL = new ThreadLocal<DateTimeFormatter>() {

        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
        }

    };

    @Override
    public Date unmarshal(String v) throws Exception {
        return DATE_FORMAT_TL.get().parseDateTime(v).toDate();
    }

    @Override
    public String marshal(Date v) throws Exception {
        DateTime dt = v==null?null:new DateTime(v);
        return dt.toString("yyyy/MM/dd HH:mm:ss");
    }
}
