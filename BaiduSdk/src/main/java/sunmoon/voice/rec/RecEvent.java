package sunmoon.voice.rec;

/**
 * Created by Sunmoon on 2018-10-28.
 */

public class RecEvent {
    private String name;
    private String params;
    private byte[] data;
    private int offset;
    private int length;

    public RecEvent(String name, String params, byte[] data, int offset, int length) {
        this.name = name;
        this.params = params;
        this.data = data;
        this.offset = offset;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
