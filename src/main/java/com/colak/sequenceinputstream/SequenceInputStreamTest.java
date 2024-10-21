package com.colak.sequenceinputstream;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

@Slf4j
public class SequenceInputStreamTest {

    public static void main() {
        // Create two byte arrays to be used as data sources
        byte[] data1 = "Hello, ".getBytes();
        byte[] data2 = "World!".getBytes();

        // Create ByteArrayInputStreams for both data arrays
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(data1);
        ByteArrayInputStream inputStream2 = new ByteArrayInputStream(data2);

        // Combine the two input streams using SequenceInputStream
        try (SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2)) {
            int byteData;
            // Read and print data from the SequenceInputStream
            while ((byteData = sequenceInputStream.read()) != -1) {
                System.out.print((char) byteData);
            }
        } catch (IOException exception) {
            log.error("Exception" , exception);
        }
    }
}
