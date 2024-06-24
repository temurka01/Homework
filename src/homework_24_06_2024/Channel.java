package homework_24_06_2024;

public class Channel {
    private String channel_name;
    private Program[] programs;

    public Channel(String channel_name, Program... programs) {
        setChannel_name(channel_name);
        setPrograms(programs);
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public Program[] getPrograms() {
        return programs;
    }

    public void setPrograms(Program... programs) {
        this.programs = programs;
    }
}
