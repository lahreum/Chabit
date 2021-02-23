import Setting from "../views/setting/Setting.vue";
import ModifyUser from "../views/setting/ModifyUser.vue";
import ManageMember from "../views/setting/ManageMember.vue";

export default [
  {
    path: "/setting",
    name: "Setting",
    component: Setting,
  },
  {
    path: "/modify-user",
    name: "ModifyUser",
    component: ModifyUser,
  },
  {
    path: "/manage-member",
    name: "ManageMember",
    component: ManageMember,
  },
];
