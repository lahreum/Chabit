import Challenge from '../views/challenge/Challenge.vue'
import NewChallengeList from '../views/challenge/NewChallengeList.vue'
import HotChallengeList from '../views/challenge/HotChallengeList.vue'
import SearchChallengeList from '../views/challenge/SearchChallengeList.vue'
import CreateChallenge from '../views/challenge/CreateChallenge.vue'
import ChallengeDetail from '../views/challenge/ChallengeDetail.vue'
import Proof from '../views/challenge/Proof.vue'
import FinishProof from '../views/challenge/FinishProof.vue'
import ProofList from '../views/challenge/ProofList.vue'
import WriteChallengeReview from '../views/challenge/WriteChallengeReview.vue'
import PersonalChallenge from '../views/challenge/PersonalChallenge.vue'

export default [
    {
        path: '/challenge',
        name: 'Challenge',
        component: Challenge,
        children: [
          { 
            path: 'new-challenge-list',
            name: 'NewChallengeList',
            component: NewChallengeList
          },
          { 
            path: 'hot-challenge-list',
            name: 'HotChallengeList',
            component: HotChallengeList
          },
          { 
            path: 'search-challenge-list',
            name: 'SearchChallengeList',
            component: SearchChallengeList
          },
        ]
      },
      { 
        path: '/create-challenge',
        name: 'CreateChallenge',
        component: CreateChallenge
      },
      { 
        path: '/challenge-detail',
        name: 'ChallengeDetail',
        component: ChallengeDetail,
      },
      { 
        path: '/write-challenge-review',
        name: 'WriteChallengeReview',
        component: WriteChallengeReview
      },
      { 
        path: '/proof',
        name: 'Proof',
        component: Proof
      },
      { 
        path: '/finish-proof',
        name: 'FinishProof',
        component: FinishProof
      },
      { 
        path: '/proof-list',
        name: 'ProofList',
        component: ProofList
      },
      {
        path: '/personal-challenge',
        name: 'PersonalChallenge',
        component: PersonalChallenge
      },
]