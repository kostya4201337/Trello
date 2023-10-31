import {IBoardEntity} from "../../../model/entity/IBoardEntity";

export type DeleteBoardProp = {
    board: IBoardEntity,
    deleteBoard: (id: number) => void
}