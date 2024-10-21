package com.colak.bufferedinputstream;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

// BufferedIputStream’s mark and reset methods allow us to read the stream multiple times.
@Slf4j
public class BufferedInputStreamMarkTest {

    public static void main() {
        String data = "Hello, BufferedInputStream!";
        byte[] byteArray = data.getBytes();

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(byteArray))) {
            testMark(bufferedInputStream);
        } catch (IOException exception) {
            log.error("Exception", exception);
        }
    }

    private static void testMark(InputStream inputStream) throws IOException {
        // not all inputStreams provide this support.
        // markSupported() is used to determine if this behavior will be exhibited by a given inputStream or not.
        if (inputStream.markSupported()) {
            // Mark the current position in the stream (limit of 10 bytes to be read before reset fails)
            // Mark method takes an integer as argument(readLimit) which represents a limit that is placed on the bytes that can be
            // read before the call to the reset() method, and if that is exceeded, the mark index is invalidated.
            inputStream.mark(10);

            // Read the first 5 bytes
            for (int i = 0; i < 5; i++) {
                System.out.print((char) inputStream.read());
            }

            System.out.println();

            // Reset the stream back to the marked position
            inputStream.reset();

            // Read the first 5 bytes again
            for (int i = 0; i < 5; i++) {
                System.out.print((char) inputStream.read());
            }
            System.out.println();
        }
    }
}
