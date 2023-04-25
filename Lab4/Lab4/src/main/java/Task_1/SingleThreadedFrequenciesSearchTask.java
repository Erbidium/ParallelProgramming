package Task_1;

import java.util.HashMap;

public class SingleThreadedFrequenciesSearchTask implements IFrequenciesSearchTask {
    @Override
    public HashMap<Integer, Integer> countFrequencies(Folder folder) {
        var frequenciesOfWordLengths = new HashMap<Integer, Integer>();

        for (Folder subFolder : folder.getSubFolders()) {
            var subFolderFrequencies = countFrequencies(subFolder);

            subFolderFrequencies.forEach((key, value) ->
                    frequenciesOfWordLengths.merge(key, value, Integer::sum));
        }
        for (Document document : folder.getDocuments()) {
            var documentFrequencies = FrequenciesCounter.calculateFrequenciesOfWorldLengths(document);

            documentFrequencies.forEach((key, value) ->
                    frequenciesOfWordLengths.merge(key, value, Integer::sum));
        }
        return frequenciesOfWordLengths;
    }
}