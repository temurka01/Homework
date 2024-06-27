package homework_24_06_2024;

public class Television {
    private Channel[] channels;

    public Television(Channel... channels) {
        setChannels(channels);
    }

    public Channel[] getChannels() {
        return channels;
    }

    public void setChannels(Channel... channels) {
        this.channels = channels;
    }
}
