import Ranking from "../views/ranking/Ranking.vue";
import RankingList from "../views/ranking/RankingList.vue";

export default [
  {
    path: "/ranking",
    name: "Ranking",
    component: Ranking,
    children: [
      {
        path: "ranking-list",
        name: "RankingList",
        component: RankingList,
        props: true,
      },
    ],
  },
];
