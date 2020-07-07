package PS;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FastIO {
    final private int BUFFER_SIZE = 1 << 16;
    final private int INT_SIZE = 10;
    private DataInputStream din;
    private DataOutputStream dout;

    private byte[] inbuffer;
    private int inbufferpointer, bytesread;
    private byte[] outbuffer;
    private int outbufferpointer;
    private byte[] bytebuffer;

    FastIO() {
        din = new DataInputStream(System.in);
        dout = new DataOutputStream(System.out);
        inbuffer = new byte[BUFFER_SIZE];
        outbuffer = new byte[BUFFER_SIZE];
        inbufferpointer = bytesread = outbufferpointer = 0;
        bytebuffer = new byte[INT_SIZE];
    }

    public int nextInt() throws IOException {
        int ret = 0;
        byte b = read();
        while (b <= ' ')
            b = read();
        boolean isneg = (b == '-');
        if (isneg)
            b = read();
        do {
            ret = ret * 10 + b - '0';
        } while ((b = read()) >= '0' && b <= '9');

        if (isneg)
            return -ret;
        return ret;
    }

    private byte read() throws IOException {
        if (inbufferpointer == bytesread)
            fillbuffer();
        return inbuffer[inbufferpointer++];
    }

    private void fillbuffer() throws IOException {
        bytesread = din.read(inbuffer, inbufferpointer = 0, BUFFER_SIZE);
        if (bytesread == -1)
            inbuffer[0] = -1;
    }

    public void write(int i) {
        if (i == 0) {
            writebuffer((byte) '0');
            return;
        }

        if (i < 0) {
            writebuffer((byte) '-');
            i = -i;
        }

        int index = 0;
        while (i > 0) {
            bytebuffer[index++] = (byte) ((i % 10) + '0');
            i /= 10;
        }

        while (index-- > 0)
            writebuffer(bytebuffer[index]);
    }

    public void writels(int i) {
        write(i);
        writebuffer((byte)' ');
    }

    private void writebuffer(byte b) {
        if (outbufferpointer == outbuffer.length) {
            flushbuffer();
        }
        outbuffer[outbufferpointer++] = b;
    }

    private void flushbuffer() {
        if (outbufferpointer != 0) {
            try {
                dout.write(outbuffer, 0, outbufferpointer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        outbufferpointer = 0;
    }
}

