import {IBoardEntity} from "../../../model/entity/IBoardEntity";

export type DeleteBoardProp = {
    board: IBoardEntity,
    onDelete: (id: number) => void
}