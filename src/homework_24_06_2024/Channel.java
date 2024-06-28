package homework_24_06_2024;

public class Channel {
    private String channelName;
    private Program[] programs;

    public Channel(String channelName, Program... programs) {
        setChannelName(channelName);
        setPrograms(programs);
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Program[] getPrograms() {
        return programs;
    }

    public void setPrograms(Program... programs) {
        this.programs = programs;
    }
}
