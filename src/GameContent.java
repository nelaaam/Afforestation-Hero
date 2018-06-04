package game.afhero.torf;

import java.util.Random;

public class GameContent {
    private final static int ARRAY_SIZE = 100;
    public String[] TrueContent = new String[ARRAY_SIZE], FalseContent = new String[ARRAY_SIZE];
    private static String[] contentStorage = new String[ARRAY_SIZE];   
    private static int ctr = 0;
    private boolean answerFlag,questionNotAvailable = true;
    private static int q;
    public GameContent() {
        this.TrueContent = getTrueQuestions();
        this.FalseContent = getFalseQuestions();
    }
    public String[] getTrueQuestions(){
    	String[] TrueContent = { 
            "A plant is a living organism and a member of the vegetable kingdom.",
            "A minute algae is a one-celled organism.",
            "Not all plants contain cholorophyll(the green colouring matter).",
            "The green colouring matter - cholorophyll - can only be found in plants.",
            "Bacteria and fungi were once considered plants but are now separated in their own kingdom.",
            "Seaweed is not a plant but is a type of algae.",
            "Mosses and liverworts are a simple type of plant with roots and leaves.",
            "The upper layers of soil are made up of about half solid material, half water and half air.",
            "Photosynthate is a compound formed in photosynthesis(the process of converting Carbon dioxide to carbohydrates).",
            "There are many flowering plants that can flower during winter such as Snowdrop.",
            "The bristlecone pine is the world's oldest growing tree.",
            "The coast redwood is the world's tallest-growing tree.",
            "Bamboo is the fastest-growing woody plant in the world; it can grow 35 inches in a single day.",
            "Tulips were so valuable in Holland that their bulbs were worth more than gold.",
            "Avocados are fruits because the bear the plants' seeds.",
            "Small pockets of air inside cranberries cause them to bounce and float in water.",
            "Rafflesia is a Southeast Asian plant called \"corpse flower\" that produces a smell like that of rotting meat.",
            "The festival of the lily-of-the-valley includes giving bouquets of lily-of-the-valley to loved ones, wishing them health and happiness.",
            "The first potatoes were cultivated in Peru about 7,000 years ago.",
            "The average strawberry has 200 seeds. It's the only fruit that bears its seeds on the outside.",
            "Trees are the longest-living organisms on earth.",
            "The favorite food of the giant panda are bamboo shoots.",
            "A cactus has flowers but no proper leaves.",
            "The protective outer layer of trees is called bark.",
            "Tomatoes are fruits.",
            "A spice is from the seed, berry, stem, bark, root or bulb of a plant.",
            "The smell of freshly cut grass is actually a plant distress call.",
            "Mustard and wasabi aren't spicy until they are crushed.",
            "The average tree is made up of 99% dead cells.",
            "The Methuselah bristlecone pine is the oldest tree in the world.",
            "The tallest tree known is called Hyperion.",
            "Arbre du Ténéré, the world's most isolated tree, was hit and destroyed by a drunk driver, despite being 250 miles from any other living tree.",
            "Drosera, also called sundews, is one of the largest carnivorous plants native in every continent except Antartica.",
            "The fear of plants is called Botanophobia.",
            "A pineapple is a berry.",
            "Strawberries, berries, cherries, and plums belong to the rose family.",
            "Arrowroot is an antidote for poisoned arrows.",
            "Around 2000 different types of plants are used by humans to make food.",
            "Ginkgo(Ginkgo biloba) is one of the oldest living tree species; it dates back to about 250 million years ago.",
            "A sunflower  is composed of hundreds of tiny flowers called florets, which ripen to become the seeds.",
            "An herb is specifically from the leaf of a plant.",
            "Plants can recognize their siblings and give the preferential treatment.",
            "The famous garden in England is dedicated to plants that kill.",
            "Cabbage, kale, cauliflower, Brussels sprouts, broccoli, Chinese cabbage and savoy are all the same species of plant.",
            "The word pineapple comes from European explorers who thought the fruit combined the look of a pinecone with flesh like that of an apple.",
            "No species of wild plant produces a flower or blossom that is absolutely black.",
            "A Quaking Aspen is the heaviest tree in the world and is also the heaviest single stem tree.",
            "The entire dandelion plant is edible.",
            "A Bonsai Orange Tree will actually produce tiny oranges.",
            "Peaches, Pears, apricots, quinces, strawberries, and apples are members of the rose family.",
            "A Gas Plant or the Burning Bush earned it's name because it's leathery green leaves, flowers and seed pods give off a strong lemon scented vapor.",
            "Angelica was used in Europe for hundreds of years as a cure for everything from the bubonic plague to indigestion.",
            "The flower Angelica is sometimes known as wild celery.",
            "Bamboo flowers are very rare.",
            "Some species of bamboo develop flowers after 65 or 120 years. ",
            "Bamboo releases 30% more oxygen into the atmosphere and absorbs more carbon dioxide compared to other plants.",
            "All plants of one bamboo species develop flowers at the same time, no matter where they are located in the world.",
            "The lotus was considered a sacred flower by ancient Egyptians and was used in burial rituals.",
            "Blue cohosh, also known as squaw root or papoose root, was used by Native American women to ensure an easy labor and childbirth.",
            "The Sunflower head is actually made of many tiny flowers called florets.",
            "The Sunflower head tracks the sun's movements.",
            "Heliotropism  is the the directional growth of a plant in response to sunlight.",
            "Phototropism is the orientation of a plant or other organism in response to light.",
            "The Agave, also known as the century plant spends many years without growing any flowers, after which it grows one single bloom and dies.",
            "Monocarpic plants are those that flower, set seeds and then die.",
            "Polycarpic plants that flowers and sets seeds many times during its lifetime.",
            "Moon flowers bloom only at night, closing during the day.",
            "The Moon flower opens in the evening and lasts through the night, remaining open until it comes into contact with the morning Sun.",
            "Roses are related to apples, raspberries, cherries, peaches, plums, nectarines, pears and almonds.",
            "A Wolffia flower consists of a single pistil and stamen; it also produces the world’s smallest fruit, called a utricle.",
            "Titan Arum are the world’s largest flower.",
            "The Corpse Flower is pollinated by flies.",
            "The said oldest flower,\"the mother of all flowers\"  is thought to have bloomed more than 125 million years ago in China. ",
            "Moon flowers open in the evening so they can be pollinated by night-flying moths.",
            "Chrysanthemums are associated with funerals in Malta and are considered unlucky.",
            "Ancient civilizations burned aster leaves to ward off evil spirits.",
            "Gas plants produce a clear gas on humid, warm nights.",
            "Almost 60 percent of fresh-cut flowers grown in the U.S. come from California.",
            "The Sunflower is not the only flower that tracks the movement of the sun.",
            "The first recorded plant collectors were the soldiers in the army of Thothmes III, Pharaoh of Egypt, 3500 years ago.",
            "The cactus family is divided into more than 100 genera.",
            "A bouquet of a dozen Wolffia blooms would comfortably fit on the head of a pin.",
            "\"A Water Lily\" has been Egypt's national flower for about 4000 years.",
            "70, 000 roses went into the making of the world's largest flower bouquet.",
            "The life-cycle of flowers consists of 6 stages: seed stage, germination, growth, reproduction, pollination and spreading seeds.",
            "85% of plant life is found in the ocean.",

        };
        return TrueContent;
    }
    public String[] getFalseQuestions(){
        String[] FalseContent = {
        		"A plant is a member of the prokaryotic kingdom.   ",
        		"A minute-algae is a complex organism.	",
        		"All plants contain cholorphyll.	",
        		"Chlorophyll can be found in all living things.	",
        		"Bacteria and fungi are plants.	",
        		"Seaweed belongs to the plant kingdom that grows in water.	",
        		"Mosses and liverworts are complex types of plant without  leaves.	",
        		"The upper layers of soil are made up of pure solid.	",
        		"The complex process by which plants convert carbon dioxide into carbohydrates is called photosynthate.	",
        		"There are no flowering plants that can survive the winter season.	",
        		"The world's tallest-growing tree is the coast redwood bristlecone pine.	",
        		"The  coast redwood is the world's oldest growing tree.	",
        		"A Bamboo can grow 15 inches a day.	",
        		"During the 1600s, tulips were so valuable in Holland that their bulbs were worth more than silver.	",
        		"An avocado is a vegetable.	",
        		"Cranberries have a rubber-like property that case them to bounce and float in water.	",
        		"Rafflesia is a fragrant flower that grows in the United States.	",
        		"In France, lily-of-the-valley are offered to the death of loved ones.	",
        		"Potatoes dates to about 250 million years ago.	",
        		"Strawberry is a seedless fruit.	",
        		"Algaes are the longest-living organisms on earth.	",
        		"The favorite food of the giant panda are bamboo leaves.	",
        		"A cactus has flowers and leaves.	",
        		"The protective outer layer of trees is called wood.	",
        		"Tomatoes belong to the vegetable kingdom.",
        		"Spices are from the leaf of a plant.	",
        		"The smell of freshly cut grass comes from the water stored in the plant.	",
        		"The mustard and wasabi plants are spicy even in the root.	",
        		"A tree is made up of all living cells.	",
        		"The tree called Methuselah is the tallest tree known.	",
        		"The oldest tree known is called Hyperion.	",
        		"The world's most isolated tree, Arbre du Ténéré, still stands at the Sahara Desert.	",
        		"Sundew is a type of sunflower.	",
        		"Plantophobia is the fear of plants.	",
        		"A pineapple belongs to the rose family.	",
        		"Strawberries, berries, cherries, and plums are berries.	",
        		"Arrowroot are plants used for poisoning arrows.	",
        		"There is around 500 plants that humans can make as food.	",
        		"Ginkgo (Ginkgo biloba) dates back to about 7 thousand years ago."	,
        		"The petal of a sunflower is called florets."	,
        		"An herb is specifically from the root of a plant."	,
        		"Plants would treat other plants, even of different species, with preferential treatment."	,
        		" There's a garden in England dedicated to herbal plants."	,
        		"Cabbage, kale, cauliflower, Brussels sprouts, broccoli, Chinese cabbage and savoy all belong to different species."	,
        		"The word pineapple was a combined from a pine tree and and an apple tree."	,
        		"A black dahlia is the only absolute black plant."	,
        		"A Quaking Aspen is the lightest tree in the world and is also the heaviest single stem tree."	,
        		"Dandelion plants can become poisonous when ingested."	,
        		"The Bonsai Orange tree produces orange leaves."	,
        		"Peaches, Pears, apricots, quinces, strawberries, and apples are members of the berry family."	,
        		"A Gas Plant earned its name because it gives off a strong gas scent."	,
        		"Angelica was used in Europe for hundreds of years for courtship."	,
        		"The flower Angelica is sometimes known as wild dahlia."	,
        		"Bamboo flowers grow everyday."	,
        		"All species of bamboo develop flowers yearly. "	,
        		"Some Bamboo releases carbon dioxide compared into the atmosphere."	,
        		"All plants of all bamboo species develop flowers at the same time."	,
        		"The lotus was considered an evil flower an was used by Ancient Egyptians for burial rituals."	,
        		"Blue cohosh is a plant used for fetus inducing drugs."	,
        		"The Sunflower head is actually made of many tiny flowers called minimas."	,
        		"The sunflower head actually tracks the earth's movements."	,
        		"Phototropism  the the directional growth of a plant in response to sunlight."	,
        		"Heliotropism is the orientation of a plant or other organism in response to light."	,
        		"The Agave blooms every night and die."	,
        		"Polycarpic plants are those that flower, set seeds and then die."	,
        		"Monocarpic plants that flowers and sets seeds many times during its lifetime."	,
        		"Moon flowers starts to bloom at night and closes after a day."	,
        		"The Moon flower remains open only when it comes in contact with the moonlight."	,
        		"Almonds are not related to apples, cherries and raspberries."	,
        		"The Wolffia is the largest flower."	,
        		"Titan Arum, despite its name, is the smallest fruit."	,
        		"The Corpse Flower is pollinated by bees."	,
        		"The said oldest flower bloomed 250 million years ago."	,
        		"Moon flowes can be pollinated by flies and bees."	,
        		"Chrysanthemums are associated with love and luck."	,
        		"Ancient civilizations burned aster leaves to call on evil spirits."	,
        		"Gas plants produce a green gas on humid, warm nights."	,
        		"Almost 60 percent of fresh-cut flowers grown in the U.S. come from Los Angeles."	,
        		"The Sunflower is the only flower that tracks the movement of the sun."	,
        		"The first recorded plant collectors were the maids of Thothmes III, Pharaoh of Egypt, 3500 years ago."	,
        		"The cactus family belongs to one genera."	,
        		"A bouquet of a dozen Wolffia blooms would not comfortably fit on the head of a pin."	,
        		"Lotus has been Egypt's national flower for about 4000 years."	,
        		"50, 000 roses went into the making of the world's largest flower bouquet."	,
        		"The life-cycle of flowers consists of 5 stages: seed stage, germination, growth, reproduction, and pollination."	,
        		"The great majority of plants are found in soil."	,


        };
        return FalseContent;
    }
    public boolean checkAvailableQuestions(int q){
    	for (int cn = 1; cn < TrueContent.length; cn++) {
            if (contentStorage[cn] == TrueContent[q]) {
                return true;
            } else if (contentStorage[cn] == FalseContent[q]) {
                return true;
            } else if(contentStorage[cn] == null) {
            	return false;
            }
        }
		return false;
    }
    public void setAnswerFlag(boolean answerFlag) {
    	this.answerFlag = answerFlag;
    }
    public boolean getAnswerFlag() {
    	return this.answerFlag;
    }
    public void setContentStorage(String temp) {
    	contentStorage[ctr] = temp;
    	ctr++;
    }
    public String getQuestion() {
    	while(questionNotAvailable && contentStorage[TrueContent.length-1] == null) {
		    Random r = new Random();
		    int TorF = r.nextInt(2);
		   	int q = r.nextInt(85);
		   	setQNumber(q);
		   	questionNotAvailable = checkAvailableQuestions(q);
		   	if (TorF == 1 && !questionNotAvailable) {
		    	questionNotAvailable = true;
		   		answerFlag = true;
		   		setAnswerFlag(answerFlag);
		   		setContentStorage(TrueContent[q]);
		   		return TrueContent[q];
		   	} else if (TorF == 0 && !questionNotAvailable){
		   		questionNotAvailable = true;
		   		answerFlag = false;
		   		setAnswerFlag(answerFlag);
	    		setContentStorage(FalseContent[q]);
		   		return FalseContent[q];	
	   		}
    	}
	   	return "No More Questions Available";
    }
    public String getTrueContent() {
    	return TrueContent[q];
    }
    public void setQNumber(int qN) {
    	q = qN;
    }
    public int getQNumber() {
    	return q;
    }
}

