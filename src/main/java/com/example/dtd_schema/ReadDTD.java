package com.example.dtd_schema;

import com.sun.xml.dtdparser.DTDParser;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.InputSource;

import java.io.FileReader;

@Slf4j
public class ReadDTD {
    public void run() throws Exception {
        log.info("parse DTD...");

        InputSource inputSource = new InputSource(new FileReader("d:/dtd/catalog2.dtd"));

        DTDParser dtdParser = new DTDParser();
        DtdListener dtdListener = new DtdListener();
        dtdParser.setDtdHandler(dtdListener);
        dtdParser.parse(inputSource);


    }
}
