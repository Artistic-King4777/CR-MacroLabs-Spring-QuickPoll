package dtos;

import java.util.Collection;

public class VoteResult {

    private int totalVotes;
    private Collection<OptionCount> results;

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totlVotes) {
        this.totalVotes = totlVotes;
    }

    public Collection<OptionCount> getResults() {
        return results;
    }

    public void setResults(Collection<OptionCount> results) {
        this.results = results;
    }

}
