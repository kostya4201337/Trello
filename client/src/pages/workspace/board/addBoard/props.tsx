import {IBoardEntity} from "../../../../model/entity/IBoardEntity";

export type AddBoardProp = {
    onCreate: (board: IBoardEntity) => void
}

