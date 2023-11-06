import {ICardCreation} from "../../../../model/Card";

export type AddCardProp = {
    boardId: number
    addCard: (boardId: number, card: ICardCreation) => void
}